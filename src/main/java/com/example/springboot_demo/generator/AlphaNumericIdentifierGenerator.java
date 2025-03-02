package com.example.springboot_demo.generator;

import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.IdentifierGenerator;

import java.util.UUID;

public class AlphaNumericIdentifierGenerator implements IdentifierGenerator  {

    @Override
    public Object generate(SharedSessionContractImplementor sharedSessionContractImplementor, Object o) {
        return UUID.randomUUID().toString().replace("-", "").substring(0, 8);
    }
}
