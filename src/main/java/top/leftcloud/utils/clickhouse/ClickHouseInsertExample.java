package top.leftcloud.utils.clickhouse;

import ru.yandex.clickhouse.ClickHouseDataSource;
import ru.yandex.clickhouse.settings.ClickHouseProperties;

import java.sql.Connection;
import java.sql.Statement;

public class ClickHouseInsertExample {

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
        try (Connection connection = dataSource.getConnection();
             Statement statement = connection.createStatement()) {
            String sql = "INSERT INTO table_name (column1, column2) VALUES ('value1', 'value2')";
            sql = "create table tmp_1(id Int64, s String) engine=MergeTree order by id;";
            sql = "INSERT INTO tmp_1 (id, s) VALUES (3, 'ssss1')";
            sql = "alter table tmp_1 add column ss3 String comment '空列';";
            statement.executeUpdate(sql);
        }

    }
}
