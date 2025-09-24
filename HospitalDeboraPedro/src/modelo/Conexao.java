
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 *
 * @author 15843968650
 */
public class Conexao {

	private static final String banco = "jdbc:mysql://localhost:3307/hospital_pedrodebora?useSLL=false";
	private static final String driver = "com.mysql.cj.jdbc.Driver";
	private static final String usuario = "root";
	private static final String senha = "1234";
	private static Connection con = null;

	public Conexao() {

	}

	public static Connection getConexao() {
		if (con == null) {
			try {
				Class.forName(driver);
				con = DriverManager.getConnection(banco, usuario, senha);
			} catch (ClassNotFoundException ex) {
				System.out.println("Nao encontrou o driver: " + ex.getMessage());
			} catch (SQLException ex) {
				System.out.println("Errol na conexão" + ex.getMessage());
			}
		}
		return con;
	}

	public static PreparedStatement getPreparedStatement(String comandoSql) {
		if (con == null) {
			con = getConexao();
		}

		try {
			return con.prepareStatement(comandoSql);
		} catch (SQLException ex) {
			System.out.println("Erro de SQL: " + ex.getMessage() + "\nComando: " + comandoSql);
		}
		return null;
	}
}

