package com.tiny.dao;

import com.tiny.module.Address;

import java.util.List;

public interface IAddressDao {
    String GET_ADDRESS = "SELECT * FROM d_receive_address";

    String GET_ADDRESS_BY_USER_ID = "SELECT * FROM d_receive_address WHERE user_id = ?";

    String GET_ADDRESS_BY_ID = "SELECT * FROM d_receive_address WHERE id = ?";

    String SAVE_ADDRESS = "INSERT INTO d_receive_address (user_id, receive_name, " +
            "full_address, postal_code, mobile, phone) VALUES (?, ?, ?, ?, ?, ?)";

    Address getAddress();

    List<Address> getAddressByUserId(int userId);

    Address getAddressById(int id);

    void saveAddress(Address address);

}
