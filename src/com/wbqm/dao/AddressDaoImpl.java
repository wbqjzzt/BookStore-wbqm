package com.wbqm.dao;

import com.wbqm.module.Address;
import com.wbqm.util.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class AddressDaoImpl implements IAddressDao {
    @Override
    public Address getAddress() {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            connection = DBUtil.getConnection();
            statement = connection.prepareStatement(GET_ADDRESS);
            resultSet = statement.executeQuery();

            if (resultSet.next()) {
                Address address = new Address();
                address.setId(resultSet.getInt("id"));
                address.setReceiveName(resultSet.getString("receive_name"));
                address.setPostalCode(resultSet.getString("postal_code"));
                address.setPhone(resultSet.getString("phone"));
                address.setFullAddress(resultSet.getString("full_address"));
                address.setMobile(resultSet.getString("mobile"));
                address.setUserId(resultSet.getInt("user_id"));

                return address;
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(resultSet, connection, statement);
        }
        return null;
    }

    @Override
    public List<Address> getAddressByUserId(int userId) {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        List<Address> addresses = new ArrayList<>();

        try {
            connection = DBUtil.getConnection();
            statement = connection.prepareStatement(GET_ADDRESS_BY_USER_ID);
            statement.setInt(1, userId);
            resultSet = statement.executeQuery();

            if (resultSet.next()) {
                Address address = new Address();
                address.setId(resultSet.getInt("id"));
                address.setFullAddress(resultSet.getString("full_address"));
                address.setReceiveName(resultSet.getString("receive_name"));
                address.setPostalCode(resultSet.getString("postal_code"));
                address.setMobile(resultSet.getString("mobile"));
                address.setPhone(resultSet.getString("phone"));
                address.setUserId(resultSet.getInt("user_id"));

                addresses.add(address);
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(resultSet, connection, statement);
        }
        return addresses;
    }

    @Override
    public Address getAddressById(int id) {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            connection = DBUtil.getConnection();
            statement = connection.prepareStatement(GET_ADDRESS_BY_ID);
            statement.setInt(1, id);
            resultSet = statement.executeQuery();

            if (resultSet.next()) {
                Address address = new Address();
                address.setId(resultSet.getInt("id"));
                address.setFullAddress(resultSet.getString("full_address"));
                address.setReceiveName(resultSet.getString("receive_name"));
                address.setPostalCode(resultSet.getString("postal_code"));
                address.setMobile(resultSet.getString("mobile"));
                address.setPhone(resultSet.getString("phone"));
                address.setUserId(resultSet.getInt("user_id"));

                return address;
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(resultSet, connection, statement);
        }
        return null;
    }

    @Override
    public void saveAddress(Address address) {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = DBUtil.getConnection();
            statement = connection.prepareStatement(SAVE_ADDRESS);

            statement.setInt(1, address.getUserId());
            statement.setString(2, address.getReceiveName());
            statement.setString(3, address.getFullAddress());
            statement.setString(4, address.getPostalCode());
            statement.setString(5, address.getMobile());
            statement.setString(6, address.getPhone());

            statement.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(connection, statement);
        }
    }
}
