package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import beans.ProductoDTO;
import interfaces.ProductoDAO;
import utils.MySQLDBConexion;

public class MySQLProductoDAO implements ProductoDAO {
	
	@Override
	public List<ProductoDTO> listarProducto() {
		List<ProductoDTO> data = new ArrayList<ProductoDTO>();
		ProductoDTO obj = null;
		Connection cn = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		try {
			cn = MySQLDBConexion.getConexion();
			String sql = "select * from TB_Producto";
			pstm = cn.prepareStatement(sql);
			rs = pstm.executeQuery();
			while (rs.next()) {
				obj = new ProductoDTO();
				obj.setCod_pro(rs.getInt(1));
				obj.setNom_pro(rs.getString(2));
				obj.setStock(rs.getInt(3));
				obj.setPre_producto(rs.getDouble(4));
				obj.setFech_ven(rs.getString(5));
				data.add(obj);
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return data;
	}
	@Override
	public ProductoDTO buscarProducto(int cod) {
		ProductoDTO obj = null;
		Connection cn = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		try {
			cn = MySQLDBConexion.getConexion();
			String sql = "select * from tb_Producto where cod_pro=?";
			pstm = cn.prepareStatement(sql);
			pstm.setInt(1, cod);
			rs = pstm.executeQuery();
			if (rs.next()) {
				obj = new ProductoDTO();
				obj.setCod_pro(rs.getInt(1));
				obj.setNom_pro(rs.getString(2));
				obj.setStock(rs.getInt(3));
				obj.setPre_producto(rs.getDouble(4));
				obj.setFech_ven(rs.getString(5));
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return obj;
	}
	@Override
	public List<ProductoDTO> buscarProductoPorNombre(String nombre) {
		List<ProductoDTO> data = new ArrayList<ProductoDTO>();
		ProductoDTO obj = null;
		Connection cn = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		try {
			cn = MySQLDBConexion.getConexion();
			String sql = "SELECT * FROM TB_Producto WHERE nom_pro LIKE ?";
			pstm = cn.prepareStatement(sql);
			pstm.setString(1, nombre + "%");
			rs = pstm.executeQuery();
			while (rs.next()) {
				obj = new ProductoDTO();
				obj.setCod_pro(rs.getInt(1));
				obj.setNom_pro(rs.getString(2));
				obj.setStock(rs.getInt(3));
				obj.setPre_producto(rs.getDouble(4));
				obj.setFech_ven(rs.getString(5));
				data.add(obj);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// ...
		}
		return data;
	}
	@Override
	public int registrarProducto(ProductoDTO obj) {
		int estado = -1;
		Connection cn = null;
		PreparedStatement pstm = null;
		try {
			cn = MySQLDBConexion.getConexion();
			String sql = "insert into TB_Producto values (null, ?, ?, ?, ?)";
			pstm = cn.prepareStatement(sql);			
			pstm.setString		(1, obj.getNom_pro());
			pstm.setInt			(2, obj.getStock());
			pstm.setDouble		(3, obj.getPre_producto());
			pstm.setString		(4, obj.getFech_ven());
			estado = pstm.executeUpdate();
			
			if (estado > 0) {
                System.out.println("El producto se registró exitosamente.");
            } else {
                System.out.println("No se registro el producto.");
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error al registrar el producto.");
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
	public int actualizarProducto(ProductoDTO obj) {
		int estado = -1;
		Connection cn = null;
		PreparedStatement pstm = null;
		try {
			cn = MySQLDBConexion.getConexion();
			String sql = "update TB_Producto set nom_pro=?, stock=?, pre_producto=?, fech_ven=? where cod_pro=?";
			pstm = cn.prepareStatement(sql);
			pstm.setString		(1, obj.getNom_pro());
			pstm.setInt			(2, obj.getStock());
			pstm.setDouble		(3, obj.getPre_producto());
			pstm.setString		(4, obj.getFech_ven());
			pstm.setInt			(5, obj.getCod_pro());
			estado = pstm.executeUpdate();
			if (estado > 0) {
                System.out.println("El producto se actualizó exitosamente.");
            } else {
                System.out.println("No se pudo actualizar el producto.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Ocurrió un error de base de datos al actualizar el producto.");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Ocurrió un error al actualizar el producto.");
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
	public int eliminarProducto(int cod) {
		int estado = -1;
		Connection cn = null;
		PreparedStatement pstm = null;
		try {
			cn = MySQLDBConexion.getConexion();
			String sql = "delete from TB_Producto where cod_pro=?";
			pstm = cn.prepareStatement(sql);
			pstm.setInt(1, cod);
			estado = pstm.executeUpdate();
			if (estado > 0) {
                System.out.println("El producto se eliminó exitosamente.");
            } else {
                System.out.println("No se pudo eliminar el producto.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error de base de datos al eliminar el producto.");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error al eliminar el producto.");
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
