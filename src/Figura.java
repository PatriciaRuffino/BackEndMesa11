import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class Figura {
    private static final String sqlCreateTable = "DROP TABLE IF EXISTS SHAPE; CREATE TABLE SHAPE"
            +"("
            +"ID INT PRIMARY KEY,"
            +"FORMA VARCHAR(255) NOT NULL,"
            +"COR VARCHAR(255) NOT NULL"
            +")";
    private static final String sqlInsert1 = "INSERT INTO SHAPE (ID, FORMA, COR) VALUES (1,'CIRCULO', 'VERMELHO')";
    private static final String sqlInsert2 = "INSERT INTO SHAPE (ID, FORMA, COR) VALUES (2,'CIRCULO', 'AZUL')";
    private static final String sqlInsert3 = "INSERT INTO SHAPE (ID, FORMA, COR) VALUES (3,'QUADRADO', 'ROSA')";
    private static final String sqlInsert4 = "INSERT INTO SHAPE (ID, FORMA, COR) VALUES (4,'QUADRADO', 'VERDE')";
    private static final String sqlInsert5 = "INSERT INTO SHAPE (ID, FORMA, COR) VALUES (5,'QUADRADO', 'AMARELO')";

    private static final String sqlSelect = "SELECT * FROM SHAPE";


    public static void main(String[] args) throws Exception {
        Connection con = null;

        try {
            con = getConection();
            Statement statement = con.createStatement();
            statement.execute(sqlCreateTable);
            statement.execute(sqlInsert1);
            statement.execute(sqlInsert2);
            statement.execute(sqlInsert3);
            statement.execute(sqlInsert4);
            statement.execute(sqlInsert5);

            showDados(con, sqlSelect);
        }
        catch (Exception e) {
            e.printStackTrace();

        } finally {
            {
                if(con==null)
                    return;
                con.close();
            }
        }
    }
    public static Connection getConection() throws Exception{
        Class.forName("org.h2.Driver").getDeclaredConstructor().newInstance();
        return DriverManager.getConnection("jdbc:h2:~/test", "sa", "");
    }

    private static void showDados(Connection connection, String sqlQuery) throws Exception{
        Statement statement = connection.createStatement();
        ResultSet rs = statement.executeQuery(sqlQuery);
        while(rs.next()){
            System.out.println(rs.getInt(1) + " - "+ rs.getString(2) + "-"+ rs.getString(3));
        }

    }
}
