package top.leftcloud.utils.clickhouse;
import ru.yandex.clickhouse.ClickHouseDataSource;
import ru.yandex.clickhouse.settings.ClickHouseProperties;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class ClickHouseQueryExample {

    public static void main(String[] args) throws Exception {
        ClickHouseProperties properties = new ClickHouseProperties();
        // 设置 ClickHouse 服务器的地址和端口
        properties.setHost("localhost");
        properties.setPort(8123);
        // 设置数据库名、用户名和密码
        properties.setDatabase("default");
        properties.setUser("default");
        properties.setPassword("");

        ClickHouseDataSource dataSource = new ClickHouseDataSource("jdbc:clickhouse://localhost:8123", properties);
        String sqlS = "SELECT 1";
        sqlS = "select * from tmp_1";
//        sqlS = "select id, s from tmp_1";
        try (Connection connection = dataSource.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sqlS)) {
            boolean printHeader = true;
            while (resultSet.next()) {
                int columnCount = resultSet.getMetaData().getColumnCount();
                if (printHeader) {
                    for (int i = 0; i < columnCount; i++) {
                        System.out.print(resultSet.getMetaData().getColumnName(i + 1));
                        if ((i + 1) < columnCount) {
                            System.out.print(", \t");
                        }
                    }
                    System.out.println();
                    printHeader = false;
                }

                for (int i = 0; i < columnCount; i++) {
                    System.out.print(resultSet.getString(i + 1));
                    if ((i + 1) < columnCount) {
                        System.out.print(", \t");
                    }
                }
                System.out.println();
            }
        }
    }
}