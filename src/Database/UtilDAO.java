package Database;

import java.sql.*;

public class UtilDAO {

private DataSource data;
	
	public UtilDAO(DataSource data) {
			this.data = data;
	}
	
	public void dropDatabase(){
		
		Connection connection = data.getConnection();
		try {
			String delete = "drop SEQUENCE if EXISTS sequenza_id;"
					+ "drop table if exists Film;"
					+ "drop table if exists TVSerie;"							
					+ "drop table if exists User;"
					;
			PreparedStatement statement = connection.prepareStatement(delete);
			
			statement.executeUpdate();
			System.out.println("Executed drop database");
			
		} catch (SQLException e) {
			
			throw new PersistenceException(e.getMessage());
		} finally {
			try {
				connection.close();
			} catch (SQLException e) {
				
				throw new PersistenceException(e.getMessage());
			}
		}
	}
	
	public void createDatabase(){ // DA RIVEDERE!!!
		
		Connection connection = data.getConnection();
		try {
			
			String delete = "create SEQUENCE sequenza_id;"
					+ "create table gruppo (\"id\" bigint primary key, nome varchar(255));"
					+ "create table indirizzo (\"codice\" bigint primary key, nome varchar(255));"
					+ "create table corso (\"codice\" bigint primary key, nome varchar(255));"
					+ "create table dipartimento(\"codice\" bigint primary key,nome varchar(255));"
					+ "create table corsodilaurea(\"codice\" bigint primary key,nome varchar(255),dipartimento_codice bigint REFERENCES dipartimento(\"codice\"));"				
					+ "create table afferisce(\"id\" bigint primary key, corso_codice bigint REFERENCES corso(\"codice\"), corsodilaurea_codice bigint REFERENCES corsodilaurea(\"codice\"));"
					+ "create table studente(matricola CHARACTER(8) primary key,"				
					+ "nome VARCHAR(255),cognome VARCHAR(255),"
					+ "data_nascita DATE, gruppo_id bigint REFERENCES gruppo(\"id\"), indirizzo_codice bigint REFERENCES indirizzo(\"codice\"), password VARCHAR(255));"
					+ "create table iscritto(\"id\" bigint primary key, matricola_studente CHARACTER(8) REFERENCES studente(\"matricola\"), corso_codice bigint REFERENCES corso(\"codice\"));";
			
			PreparedStatement statement = connection.prepareStatement(delete);
			
			statement.executeUpdate();
			System.out.println("Executed create database");
			
		} catch (SQLException e) {
			
			throw new PersistenceException(e.getMessage());
		} finally {
			try {
				connection.close();
			} catch (SQLException e) {
				
				throw new PersistenceException(e.getMessage());
			}
		}
	}
	
	
	public  void resetDatabase() {
			
			Connection connection = data.getConnection();
			try {
				String delete = "delete FROM User";
				PreparedStatement statement = connection.prepareStatement(delete);
				
				statement.executeUpdate();
	
				delete = "delete FROM Film";
				statement = connection.prepareStatement(delete);
				
				delete = "delete FROM TVSerie";
				statement = connection.prepareStatement(delete);
				
				statement.executeUpdate();
			} catch (SQLException e) {
				
				throw new PersistenceException(e.getMessage());
			} finally {
				try {
					connection.close();
				} catch (SQLException e) {
					
					throw new PersistenceException(e.getMessage());
				}
			}
		}
	
}
