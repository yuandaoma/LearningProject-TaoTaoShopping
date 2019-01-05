package com.taotao.common.utils;

import java.lang.reflect.Type;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

/**
 * @author ZhuTao
 * @version 1.0
 * @Date 2017年10月2日
 * @description json相关操作
 */
public class GsonUtil {

	private static Gson gson;

	static {
		GsonBuilder gsonBuilder = new GsonBuilder();
		gsonBuilder.registerTypeAdapter(Date.class, new JsonSerializer<Date>() {

			@Override
			public JsonElement serialize(Date src, Type typeOfSrc, JsonSerializationContext context) {
				return new JsonPrimitive(src.getTime());
			}
		});
		gsonBuilder.registerTypeAdapter(Date.class, new JsonDeserializer<Date>() {

			/**
			 * 反序列化时调用
			 */
			@Override
			public Date deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
					throws JsonParseException {
				return new Date(json.getAsJsonPrimitive().getAsLong());
			}
		});
		// gsonBuilder.setLongSerializationPolicy(LongSerializationPolicy.STRING);
		gson = gsonBuilder.create();
	}

	public static Gson getGson() {
		return gson;
	}

	public static void main(String[] args) {
		Map<String, Object> retVal = new HashMap<>();
		retVal.put("name", "张");
		retVal.put("age", 1);
		retVal.put("birthday", new Date());
		String json = GsonUtil.getGson().toJson(retVal);
		System.out.println("json = " + json);

	}

}
