package silly.myself.utils

import com.google.gson.Gson
import java.text.SimpleDateFormat
import java.util.*
import org.json.JSONException
import org.json.JSONObject
import com.google.gson.JsonSyntaxException


/**
 * 解密数据
 */
fun decryptData(sign: String, timestamp: String): Map<String, String> {
    val map = mutableMapOf<String, String>()
    if (sign.isNotEmpty() && timestamp.isNotEmpty()) {
        var decrypt = DesUtil.decrypt(sign, timestamp)
        decrypt = DesUtil.decrypt(decrypt)
        val split = decrypt.split(",")
        for (kvs in split) {
            val kv = kvs.split("=")
            map[kv[0]] = kv[1]
            System.out.println("${kv[0]}:${kv[1]}")
        }
    }
    return map
}

/**
 * 成功的数据List
 * isEncrypt  是否加密
 */
fun <T> successData(list: List<T>, isEncrypt: Boolean = true): String {
    return successData(Gson().toJson(list), isEncrypt)
}

/**
 * 成功的数据Bean
 * isEncrypt  是否加密
 */
fun <T> successData(bean: T, isEncrypt: Boolean = true): String {
    return successData(bean.toString(), isEncrypt)
}

/**
 * 成功的数据String
 * isEncrypt  是否加密
 */
fun successData(str: String, isEncrypt: Boolean = true): String {
    val dataPre = "{\"msg\":0,\"param\":\"msgok\",\"data\":"
    var data = str
    if (data.isEmpty()) {
        data = "\"\""
    } else if (!isJSON(data)) {
        data = "\"$data\""
    }
    val sendData = "$dataPre$data}"
    return if (isEncrypt) DesUtil.encrypt(sendData).replace("\r\n", "")// 加密
    else sendData
}


/**
 * 失败的数据String
 */
fun failData(param: String, isEncrypt: Boolean = true, msg: Int = 1): String {
    val dataPre = "{\"msg\":$msg,\"param\":"
    var data = param.toString()
    if (data.isEmpty()) data = "\"\""
    else if (!"}".equals(data.substring(data.length - 1, data.length)) || !"]".equals(data.substring(data.length - 1, data.length))) data = "\"$data\""
    return if (isEncrypt) DesUtil.encrypt("$dataPre$data}")// 加密
    else "$dataPre$data}"
}

/**
 * 判断参数是否为空
 */
fun isEmptyParams(list: List<String>): Boolean {
    for (str in list) {
        if (str.isEmpty()) return true
    }
    return false
}

/**
 * 获取当前时间
 * yyyy-MM-dd HH:mm:ss
 */
fun getCurrentDate(): String {
    return SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault()).format(Date())
}


/**
 * 判断是否是json
 * @param jsonInString
 * @return
 */
fun isJSON(jsonInString: String): Boolean {
    return try {
        Gson().fromJson(jsonInString, Any::class.java)
        true
    } catch (ex: JsonSyntaxException) {
        false
    }
}