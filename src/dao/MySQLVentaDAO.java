package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import beans.DetalleVentaDTO;
import beans.VentaDTO;
import interfaces.VentaDAO;
import utils.MySQLDBConexion;

public class MySQLVentaDAO implements VentaDAO {

    @Override
    public List<VentaDTO> listarVenta() {
        List<VentaDTO> ventas = new ArrayList<>();
        Connection con = null;
        PreparedStatement pst = null;
        ResultSet rs = null;

        try {
            con = MySQLDBConexion.getConexion();
            String query = "SELECT v.cod_venta, c.nom_cli, v.fecha_venta, v.total_venta "
                    + "FROM TB_Ventas v "
                    + "INNER JOIN TB_Cliente c ON v.cod_cli = c.cod_cli";

            pst = con.prepareStatement(query);
            rs = pst.executeQuery();

            while (rs.next()) {
                VentaDTO venta = new VentaDTO();
                venta.setCodVenta(rs.getInt("cod_venta"));
                venta.setNom_cli(rs.getString("nom_cli"));
                venta.setFechaVenta(rs.getString("fecha_venta"));
                venta.setTotalVenta(rs.getDouble("total_venta"));
                ventas.add(venta);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) rs.close();
                if (pst != null) pst.close();
                if (con != null) con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return ventas;
    }

    @Override
    public VentaDTO buscarVenta(int cod) {
        VentaDTO venta = null;
        Connection con = null;
        PreparedStatement pst = null;
        ResultSet rs = null;

        try {
            con = MySQLDBConexion.getConexion();
            String query = "SELECT cod_venta, cod_cli, fecha_venta, total_venta FROM TB_Ventas WHERE cod_venta = ?";
            pst = con.prepareStatement(query);
            pst.setInt(1, cod);
            rs = pst.executeQuery();

            if (rs.next()) {
                venta = new VentaDTO();
                venta.setCodVenta(rs.getInt("cod_venta"));
                venta.setCod_cli(rs.getInt("cod_cli"));
                venta.setFechaVenta(rs.getString("fecha_venta"));
                venta.setTotalVenta(rs.getDouble("total_venta"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) rs.close();
                if (pst != null) pst.close();
                if (con != null) con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return venta;
    } 
    
    @Override
    public int eliminarVenta(int cod) {
    	int estado = -1;
		Connection cn = null;
		PreparedStatement pstm = null;
		try {
			cn = MySQLDBConexion.getConexion();
			String sql = "delete from TB_Ventas where cod_venta=?";
			pstm = cn.prepareStatement(sql);
			pstm.setInt(1, cod);
			estado = pstm.executeUpdate();
			if (estado > 0) {
                System.out.println("La venta se elimino exitosamente.");
            } else {
                System.out.println("No se pudo eliminar la venta.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error de base de datos al eliminar la venta.");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error al eliminar la venta.");
        } finally {

            if (pstm != null) {
                try {
                    pstm.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (cn != null) {
                try {
                    cn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return estado;

	}
    @Override
    public List<DetalleVentaDTO> obtenerDetalleVenta(int codVenta) {
        List<DetalleVentaDTO> detalleVenta = new ArrayList<>();
        Connection con = null;
        PreparedStatement pst = null;
        ResultSet rs = null;

        try {
            con = MySQLDBConexion.getConexion();
            String query = "SELECT dv.cod_det_venta, dv.cod_venta, dv.cod_pro, dv.cantidad, dv.pre_producto, p.nom_pro " +
                    "FROM TB_DetalleVenta dv " +
                    "JOIN TB_Producto p ON dv.cod_pro = p.cod_pro " +
                    "WHERE dv.cod_venta = ?";
            pst = con.prepareStatement(query);
            pst.setInt(1, codVenta);
            rs = pst.executeQuery();

            while (rs.next()) {
                DetalleVentaDTO detalle = new DetalleVentaDTO();
                detalle.setCodDetalleVenta(rs.getInt("cod_det_venta")); 
                detalle.setCodVenta(rs.getInt("cod_venta"));
                detalle.setCod_pro(rs.getInt("cod_pro"));
                detalle.setCantidad(rs.getInt("cantidad"));
                detalle.setPre_producto(rs.getDouble("pre_producto"));
                detalle.setNom_pro(rs.getString("nom_pro"));
                detalleVenta.add(detalle);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) rs.close();
                if (pst != null) pst.close();
                if (con != null) con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return detalleVenta;
    }

    @Override
	public int registrarVenta(VentaDTO obj) {
		int estado = -1;
		Connection cn = null;
		PreparedStatement pstm = null;
		try {
			cn = MySQLDBConexion.getConexion();
			String sql = "insert into TB_Ventas values (null, ?, ?, ?)";
			pstm = cn.prepareStatement(sql);			
			pstm.setInt				(1, obj.getCod_cli());
			pstm.setString			(2, obj.getFechaVenta());
			pstm.setDouble			(3, obj.getTotalVenta());
			estado = pstm.executeUpdate();
			
			if (estado > 0) {
                System.out.println("La venta se registro exitosamente.");
            } else {
                System.out.println("No se registro la Venta.");
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error al registrar la venta.");
        } finally {

            if (pstm != null) {
                try {
                    pstm.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (cn != null) {
                try {
                    cn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return estado;
		
	}

    @Override
	public int registrarDetalleVenta(DetalleVentaDTO obj) {
		int estado = -1;
		Connection cn = null;
		PreparedStatement pstm = null;
		try {
			cn = MySQLDBConexion.getConexion();
			String sql = "insert into TB_DetalleVenta values (null, ?, ?, ?, ?)";
			pstm = cn.prepareStatement(sql);			
			pstm.setInt			(1, obj.getCodVenta());
			pstm.setInt			(2, obj.getCod_pro());
			pstm.setInt			(3, obj.getCantidad());
			pstm.setDouble		(4, obj.getPre_producto());
			estado = pstm.executeUpdate();
			
			if (estado > 0) {
                System.out.println("El detalle de la venta se registro exitosamente.");
            } else {
                System.out.println("No se registro lastimosamente el Detalle de la Venta.");
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error al registrar el Detalle de la venta.");
        } finally {

            if (pstm != null) {
                try {
                    pstm.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (cn != null) {
                try {
                    cn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return estado;
	}

    @Override
	public int actualizarVenta(VentaDTO obj) {
		int estado = -1;
		Connection cn = null;
		PreparedStatement pstm = null;
		try {
			cn = MySQLDBConexion.getConexion();
			String sql = "update TB_Ventas set cod_cli=?, fecha_venta=?, total_venta=? where cod_venta=?";
			pstm = cn.prepareStatement(sql);
			pstm.setInt				(1, obj.getCod_cli());
			pstm.setString			(2, obj.getFechaVenta());
			pstm.setDouble			(3, obj.getTotalVenta());
			pstm.setInt				(4, obj.getCodVenta());
			estado = pstm.executeUpdate();
			if (estado > 0) {
                System.out.println("La venta se actualizo exitosamente.");
            } else {
                System.out.println("No se pudo actualizar la venta.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Ocurrio un error de base de datos al actualizar la venta.");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Ocurrio un error al actualizar la venta.");
        } finally {

            if (pstm != null) {
                try {
                    pstm.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (cn != null) {
                try {
                    cn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return estado;
	}
    @Override
    public int eliminarDetalleVenta(int codDet) {
    	int estado = -1;
		Connection cn = null;
		PreparedStatement pstm = null;
		try {
			cn = MySQLDBConexion.getConexion();
			String sql = "delete from TB_DetalleVenta where cod_det_venta=?";
			pstm = cn.prepareStatement(sql);
			pstm.setInt(1, codDet);
			estado = pstm.executeUpdate();
			if (estado > 0) {
                System.out.println("El detalle de venta se elimino exitosamente.");
            } else {
                System.out.println("No se pudo eliminar el detalle de la venta.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error de base de datos al eliminar el detalle de la venta.");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error al eliminar el detalle de la venta.");
        } finally {

            if (pstm != null) {
                try {
                    pstm.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (cn != null) {
                try {
                    cn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return estado;

	}

}
