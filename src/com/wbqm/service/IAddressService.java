package com.wbqm.service;

import com.wbqm.module.Address;

import java.util.List;

public interface IAddressService {

    Address getAddress();

    List<Address> getAddressByUserId(int userId);

    Address getAddressById(int id);

    void saveAddress(Address address);

}
