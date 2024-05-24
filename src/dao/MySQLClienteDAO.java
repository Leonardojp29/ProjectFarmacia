package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import beans.ClienteDTO;
import interfaces.ClienteDAO;
import utils.MySQLDBConexion;

public class MySQLClienteDAO implements ClienteDAO {

	@Override
	public List<ClienteDTO> listarCliente() {
	    List<ClienteDTO> data = new ArrayList<ClienteDTO>();
	    Connection cn = null;
	    PreparedStatement pstm = null;
	    ResultSet rs = null;
	    try {
	        cn = MySQLDBConexion.getConexion();
	        String sql = "SELECT * FROM TB_Cliente";
	        pstm = cn.prepareStatement(sql);
	        rs = pstm.executeQuery();
	        while (rs.next()) {
	            ClienteDTO cliente = new ClienteDTO();
	            cliente.setCod_cli(rs.getInt(1));
	            cliente.setNom_cli(rs.getString(2));
	            cliente.setDireccion(rs.getString(3));
	            cliente.setTelefono(rs.getString(4));
	            cliente.setCorreo(rs.getString(5));
	            cliente.setDni(rs.getString(6)); 
	            data.add(cliente);
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    } finally {
	        if (rs != null) {
	            try {
	                rs.close();
	            } catch (SQLException e) {
	                e.printStackTrace();
	            }
	        }
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
	    return data;
	}

	@Override
	public ClienteDTO buscarCliente(int codCli) {
	    ClienteDTO cliente = null;
	    Connection cn = null;
	    PreparedStatement pstm = null;
	    ResultSet rs = null;
	    try {
	        cn = MySQLDBConexion.getConexion();
	        String sql = "SELECT * FROM TB_Cliente WHERE cod_cli = ?";
	        pstm = cn.prepareStatement(sql);
	        pstm.setInt(1, codCli);
	        rs = pstm.executeQuery();
	        if (rs.next()) {
	            cliente = new ClienteDTO();
	            cliente.setCod_cli(rs.getInt(1));
	            cliente.setNom_cli(rs.getString(2));
	            cliente.setDireccion(rs.getString(3));
	            cliente.setTelefono(rs.getString(4));
	            cliente.setCorreo(rs.getString(5));
	            cliente.setDni(rs.getString(6)); 
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    } finally {
	        if (rs != null) {
	            try {
	                rs.close();
	            } catch (SQLException e) {
	                e.printStackTrace();
	            }
	        }
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
	    return cliente;
	}

	@Override
	public int registrarCliente(ClienteDTO cliente) {
	    int estado = -1;
	    Connection cn = null;
	    PreparedStatement pstm = null;
	    try {
	        cn = MySQLDBConexion.getConexion();
	        String sql = "INSERT INTO TB_Cliente VALUES (null, ?, ?, ?, ?, ?)";
	        pstm = cn.prepareStatement(sql);
	        pstm.setString(1, cliente.getNom_cli());
	        pstm.setString(2, cliente.getDireccion());
	        pstm.setString(3, cliente.getTelefono());
	        pstm.setString(4, cliente.getCorreo());
	        pstm.setString(5, cliente.getDni());
	        estado = pstm.executeUpdate();

	        if (estado > 0) {
	            System.out.println("El cliente se registr� exitosamente.");
	        } else {
	            System.out.println("No se registr� el cliente.");
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	        System.out.println("Error al registrar el cliente.");
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
	public int actualizarCliente(ClienteDTO cliente) {
	    int estado = -1;
	    Connection cn = null;
	    PreparedStatement pstm = null;
	    try {
	        cn = MySQLDBConexion.getConexion();
	        String sql = "UPDATE TB_Cliente SET nom_cli = ?, direccion = ?, telefono = ?, correo = ?, dni = ? WHERE cod_cli = ?";
	        pstm = cn.prepareStatement(sql);
	        pstm.setString(1, cliente.getNom_cli());
	        pstm.setString(2, cliente.getDireccion());
	        pstm.setString(3, cliente.getTelefono());
	        pstm.setString(4, cliente.getCorreo());
	        pstm.setString(5, cliente.getDni());
	        pstm.setInt(6, cliente.getCod_cli());
	        estado = pstm.executeUpdate();
	        if (estado > 0) {
	            System.out.println("El cliente se actualiz� exitosamente.");
	        } else {
	            System.out.println("No se pudo actualizar el cliente.");
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	        System.out.println("Ocurri� un error de base de datos al actualizar el cliente.");
	    } catch (Exception e) {
	        e.printStackTrace();
	        System.out.println("Ocurri� un error al actualizar el cliente.");
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
	public int eliminarCliente(int codCli) {
	    int estado = -1;
	    Connection cn = null;
	    PreparedStatement pstm = null;
	    try {
	        cn = MySQLDBConexion.getConexion();
	        String sql = "DELETE FROM TB_Cliente WHERE cod_cli = ?";
	        pstm = cn.prepareStatement(sql);
	        pstm.setInt(1, codCli);
	        estado = pstm.executeUpdate();
	        if (estado > 0) {
	            System.out.println("El cliente se elimin� exitosamente.");
	        } else {
	            System.out.println("No se pudo eliminar el cliente.");
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	        System.out.println("Error de base de datos al eliminar el cliente.");
	    } catch (Exception e) {
	        e.printStackTrace();
	        System.out.println("Error al eliminar el cliente.");
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


		public List<ClienteDTO> buscarClientePorNombre(String nombre) {
			List<ClienteDTO> listaClientes = new ArrayList<ClienteDTO>();
			ClienteDTO obj = null;
			Connection cn = null;
			PreparedStatement pstm = null;
			ResultSet rs = null;
			try {
				cn = MySQLDBConexion.getConexion();
				String sql = "SELECT * FROM TB_Cliente WHERE nom_cli LIKE ?";
				pstm = cn.prepareStatement(sql);
				pstm.setString(1, nombre + "%");
				rs = pstm.executeQuery();
				while (rs.next()) {
					obj = new ClienteDTO();
					obj.setCod_cli(rs.getInt(1));
					obj.setNom_cli(rs.getString(2));
					obj.setDireccion(rs.getString(3));
					obj.setTelefono(rs.getString(4));
					obj.setCorreo(rs.getString(5));
					obj.setDni(rs.getString(6));
					listaClientes.add(obj);
				}
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				
			}
			return listaClientes;
	}
}
