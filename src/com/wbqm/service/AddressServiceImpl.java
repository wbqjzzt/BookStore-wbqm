package com.wbqm.service;

import com.wbqm.dao.DaoFactory;
import com.wbqm.module.Address;

import java.util.List;

public class AddressServiceImpl implements IAddressService {

    @Override
    public Address getAddress() {
        return DaoFactory.getAddressDao().getAddress();
    }

    public List<Address> getAddressByUserId(int userId) {
        return DaoFactory.getAddressDao().getAddressByUserId(userId);
    }

    public Address getAddressById(int id) {
        return DaoFactory.getAddressDao().getAddressById(id);
    }

    public void saveAddress(Address address) {
        DaoFactory.getAddressDao().saveAddress(address);
    }
}
