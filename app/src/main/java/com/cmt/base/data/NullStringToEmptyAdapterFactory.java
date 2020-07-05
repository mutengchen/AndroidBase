package com.cmt.base.data;

import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.TypeAdapterFactory;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;

import java.io.IOException;

/*
 * Created by cmt on 2019/7/31
 */
public class NullStringToEmptyAdapterFactory implements TypeAdapterFactory {
    @SuppressWarnings("unchecked")
    public <T> TypeAdapter<T> create(Gson gson, TypeToken<T> type) {
        Class<T> rawType = (Class<T>) type.getRawType();
        if (rawType == String.class) {
            return null;
        }
        if(rawType == Integer.class){
            return (TypeAdapter<T>) new IntegerNullAdapter();
        }
        if(rawType == Double.class){
            return (TypeAdapter<T>)new DoubleNullAdapter();
        }

        return (TypeAdapter<T>) new StringNullAdapter();
    }
    public class DoubleNullAdapter extends TypeAdapter<Double> {

        @Override
        public void write(JsonWriter writer, Double value) throws IOException {
            if (value == null) {
                writer.nullValue();
                return;
            }
            writer.value(value);
        }

        @Override
        public Double read(JsonReader reader) throws IOException {
            if (reader.peek() == JsonToken.NULL) {
                reader.nextNull();
                return 0.0;
            }
            return reader.nextDouble();
        }
    }
    //处理整数位空
    public class IntegerNullAdapter extends TypeAdapter<Integer> {

        @Override
        public void write(JsonWriter writer, Integer value) throws IOException {
            if (value == null) {
                writer.nullValue();
                return;
            }
            writer.value(value);
        }

        @Override
        public Integer read(JsonReader reader) throws IOException {
            if (reader.peek() == JsonToken.NULL) {
                reader.nextNull();
                return 0;
            }
            return reader.nextInt();
        }
    }
    public class StringNullAdapter extends TypeAdapter<String> {
        @Override
        public String read(JsonReader reader) throws IOException {
            // TODO Auto-generated method stub
            if (reader.peek() == JsonToken.NULL) {
                reader.nextNull();
                return "";
            }
            return reader.nextString();
        }
        @Override
        public void write(JsonWriter writer, String value) throws IOException {
            // TODO Auto-generated method stub
            if (value == null) {
                writer.nullValue();
                return;
            }
            writer.value(value);
        }
    }


}
