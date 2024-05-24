package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import beans.PersonalDTO;
import interfaces.PersonalDAO;
import utils.MySQLDBConexion;

public class MySQLPersonalDAO implements PersonalDAO {

    @Override
    public List<PersonalDTO> listarPersonal() {
        List<PersonalDTO> data = new ArrayList<>();
        PersonalDTO obj = null;
        Connection cn = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;
        try {
            cn = MySQLDBConexion.getConexion();
            String sql = "SELECT * FROM TB_Personal";
            pstm = cn.prepareStatement(sql);
            rs = pstm.executeQuery();
            while (rs.next()) {
                obj = new PersonalDTO();
                obj.setCod_per(rs.getInt(1));
                obj.setNom_per(rs.getString(2));
                obj.setApe_per(rs.getString(3));
                obj.setDni(rs.getString(4));
                obj.setLogin(rs.getString(5));
                obj.setClave(rs.getString(6));
                data.add(obj);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (pstm != null) {
                    pstm.close();
                }
                if (cn != null) {
                    cn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return data;
    }

    @Override
    public PersonalDTO buscarPersonal(int cod) {
        PersonalDTO obj = null;
        Connection cn = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;
        try {
            cn = MySQLDBConexion.getConexion();
            String sql = "SELECT * FROM TB_Personal WHERE cod_per=?";
            pstm = cn.prepareStatement(sql);
            pstm.setInt(1, cod);
            rs = pstm.executeQuery();
            if (rs.next()) {
                obj = new PersonalDTO();
                obj.setCod_per(rs.getInt(1));
                obj.setNom_per(rs.getString(2));
                obj.setApe_per(rs.getString(3));
                obj.setDni(rs.getString(4));
                obj.setLogin(rs.getString(5));
                obj.setClave(rs.getString(6));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (pstm != null) {
                    pstm.close();
                }
                if (cn != null) {
                    cn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return obj;
    }

    @Override
    public int registrarPersonal(PersonalDTO obj) {
        int estado = -1;
        Connection cn = null;
        PreparedStatement pstm = null;
        try {
            cn = MySQLDBConexion.getConexion();
            String sql = "INSERT INTO TB_Personal VALUES (null, ?, ?, ?, ?, ?)";
            pstm = cn.prepareStatement(sql);
            pstm.setString(1, obj.getNom_per());
            pstm.setString(2, obj.getApe_per());
            pstm.setString(3, obj.getDni());
            pstm.setString(4, obj.getLogin());
            pstm.setString(5, obj.getClave());
            estado = pstm.executeUpdate();

            if (estado > 0) {
                System.out.println("El personal se registró exitosamente.");
            } else {
                System.out.println("No se registró el personal.");
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error al registrar el personal.");
        } finally {
            try {
                if (pstm != null) {
                    pstm.close();
                }
                if (cn != null) {
                    cn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return estado;
    }

    @Override
    public int actualizarPersonal(PersonalDTO obj) {
        int estado = -1;
        Connection cn = null;
        PreparedStatement pstm = null;
        try {
            cn = MySQLDBConexion.getConexion();
            String sql = "UPDATE TB_Personal SET nom_per=?, ape_per=?, dni=?, login=?, clave=? WHERE cod_per=?";
            pstm = cn.prepareStatement(sql);
            pstm.setString(1, obj.getNom_per());
            pstm.setString(2, obj.getApe_per());
            pstm.setString(3, obj.getDni());
            pstm.setString(4, obj.getLogin());
            pstm.setString(5, obj.getClave());
            pstm.setInt(6, obj.getCod_per());
            estado = pstm.executeUpdate();

            if (estado > 0) {
                System.out.println("El personal se actualizó exitosamente.");
            } else {
                System.out.println("No se pudo actualizar el personal.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Ocurrió un error de base de datos al actualizar el personal.");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Ocurrió un error al actualizar el personal.");
        } finally {
            try {
                if (pstm != null) {
                    pstm.close();
                }
                if (cn != null) {
                    cn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return estado;
    }

    @Override
    public int eliminarPersonal(int cod) {
        int estado = -1;
        Connection cn = null;
        PreparedStatement pstm = null;
        try {
            cn = MySQLDBConexion.getConexion();
            String sql = "DELETE FROM TB_Personal WHERE cod_per=?";
            pstm = cn.prepareStatement(sql);
            pstm.setInt(1, cod);
            estado = pstm.executeUpdate();

            if (estado > 0) {
                System.out.println("El personal se eliminó exitosamente.");
            } else {
                System.out.println("No se pudo eliminar el personal.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error de base de datos al eliminar el personal.");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error al eliminar el personal.");
        } finally {
            try {
                if (pstm != null) {
                    pstm.close();
                }
                if (cn != null) {
                    cn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return estado;
    }
    @Override
    public PersonalDTO iniciarSesion(String login) {
        PersonalDTO obj = null;
        Connection cn = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;
        try {
            cn = MySQLDBConexion.getConexion();
            String sql = "SELECT * FROM TB_Personal where login=?";
            pstm = cn.prepareStatement(sql);
            pstm.setString(1, login);
            rs = pstm.executeQuery();
            if (rs.next()) {
                obj = new PersonalDTO();
                obj.setCod_per(rs.getInt(1));
                obj.setNom_per(rs.getString(2));
                obj.setApe_per(rs.getString(3));
                obj.setDni(rs.getString(4));
                obj.setLogin(rs.getString(5));
                obj.setClave(rs.getString(6));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return obj;
}
}

