package com.nexasolutions.nexa.utils;

import org.hibernate.id.IdentifierGenerator;

import java.nio.ByteBuffer;
import java.util.UUID;

public class UuidV7Generator implements IdentifierGenerator {

    @Override
    public Object generate(org.hibernate.engine.spi.SharedSessionContractImplementor session, Object object) {
        long timestamp = System.currentTimeMillis();

        ByteBuffer buffer = ByteBuffer.allocate(16);
        buffer.putLong(0, timestamp);

        UUID randomPart = UUID.randomUUID();

        long mostSigBits = buffer.getLong(0);
        long leastSigBits = randomPart.getLeastSignificantBits();

        return new UUID(mostSigBits, leastSigBits);
    }
}
