package com.github.jvanheesch;

import org.springframework.http.HttpInputMessage;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.MediaType;
import org.springframework.http.converter.AbstractHttpMessageConverter;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.http.converter.StringHttpMessageConverter;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.util.UUID;

public class UuidHttpMessageConverter extends AbstractHttpMessageConverter<UUID> {
    private final StringHttpMessageConverter stringHttpMessageConverter;

    public UuidHttpMessageConverter(StringHttpMessageConverter stringHttpMessageConverter) {
        this(StringHttpMessageConverter.DEFAULT_CHARSET, stringHttpMessageConverter);
    }

    public UuidHttpMessageConverter(Charset defaultCharset, StringHttpMessageConverter stringHttpMessageConverter) {
        super(defaultCharset, MediaType.TEXT_PLAIN);

        this.stringHttpMessageConverter = stringHttpMessageConverter;
    }

    @Override
    protected boolean supports(Class<?> clazz) {
        return clazz == UUID.class;
    }

    @Override
    protected UUID readInternal(Class<? extends UUID> clazz, HttpInputMessage inputMessage) throws IOException, HttpMessageNotReadableException {
        String read = stringHttpMessageConverter.read(String.class, inputMessage);
        System.out.println(read);
        return UUID.fromString(read);
    }

    @Override
    protected void writeInternal(UUID uuid, HttpOutputMessage outputMessage) throws IOException, HttpMessageNotWritableException {
        throw new UnsupportedEncodingException();
    }
}
