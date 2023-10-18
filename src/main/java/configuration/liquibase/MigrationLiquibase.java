package configuration.liquibase;

import configuration.ConfigParser;
import liquibase.Liquibase;
import liquibase.database.Database;
import liquibase.database.DatabaseFactory;
import liquibase.database.jvm.JdbcConnection;
import liquibase.resource.ClassLoaderResourceAccessor;

import java.sql.Connection;
import java.sql.DriverManager;

public class MigrationLiquibase {
    public static void migrate() {
        try {
            ConfigParser config = new ConfigParser();
            Connection connection = DriverManager.getConnection(
                    config.getProperty("url"),
                    config.getProperty("username"),
                    config.getProperty("password")
            );
            Database database = DatabaseFactory.getInstance().findCorrectDatabaseImplementation(new JdbcConnection(connection));
            database.setDefaultSchemaName(config.getProperty("defaultSchemaName"));
            Liquibase liquibase = new Liquibase("db/changelog-master.xml", new ClassLoaderResourceAccessor(), database);
            liquibase.update();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
