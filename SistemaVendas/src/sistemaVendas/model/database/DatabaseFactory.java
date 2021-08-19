package sistemaVendas.model.database;

public class DatabaseFactory {
    public static Database getDatabase(String nome){
        if(nome.equals("postgresql")){
            return new DatabasePostgreSQL();
        }else if(nome.equals("SQLServer")){
            return new DatabaseSQLServer();
        }
        return null;
    }
}
