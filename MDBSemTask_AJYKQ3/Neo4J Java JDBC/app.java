package neo4j;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Properties;

import neo4j.filmT;


public class app {
	
	public static void main(String[] args) {
		add_films();
		read_films(1800);
		
		System.out.println("A Querynek vége, kilépés.\n");
	}
	
	public static void add_films() {
		try {
			Class.forName("org.neo4j.jdbc.Driver");
			Properties props = new Properties();
			props.setProperty("user", "neo4j");
			props.setProperty("password", "neo4j");
			Connection con = DriverManager.getConnection("jdbc:neo4j:http://localhost:8389", props);
			String query;
			
			
			
			filmT[] films = new filmT[5];
			films[0] = new filmT('1', 'The Shawshank Redemption', 1994, 'Frank Darabont', 1100);
			films[1] = new filmT('2', 'The Godfather', 1972, 'Francis Ford Coppola', 900);
			films[2] = new filmT('3', 'The Dark Knight', 2008, 'Christopher Nolan', 1300);
			films[3] = new filmT('4', 'Pulp Fiction', 1994, 'Quentin Tarantino', 1000);
			films[4] = new filmT('5', 'Forrest Gump', 1994, 'Robert Zemeckis', 950);
			
			
			
			query = "CREATE (a:film {_id:{1}, name:{2}, releaseDate:{3}, director:{4}, price:{5}})";
			
			PreparedStatement stmt = con.prepareStatement(query);
			
			for (int i=0; i<6; i++) {
				stmt.setString(1, films[i].id);
				stmt.setString(2, films[i].name);
				stmt.setInt(3, films[i].releaseDate);
				stmt.setString(4, films[i].director);
				stmt.setString(5, films[i].price);
				
				stmt.executeUpdate();
			}
			
			con.close();
			
		}	catch(Exception e) {
				System.out.println("Hiba történt: " + e.getMessage());
	}
}
	
	public static void read_films(int priceLimit) {
		
		try
		{
			Class.forName("org.neo4j.jdbc.Driver");
			Properties props = new Properties();
			props.setProperty("user", "neo4j");
			props.setProperty("password", "neo4j");
			Connection con = DriverManager.getConnection("jdbc:neo4j:http://localhost:8389",props);
			
			String query = "MATCH (A:film) WHERE A.price > {1} RETURN A.name";
			PreparedStatement stmt = con.prepareStatement(query);
			stmt.setInt(1, priceLimit);
			ResultSet rs = stmt.executeQuery();
			while(rs.next()) {
				System.out.println(rs.getString("A.name"));
			}
			
			con.close();
			
		}	catch(Exception e) {
				System.out.println("Hiba történt: " + e.getMessage());
		}
	}
	
}