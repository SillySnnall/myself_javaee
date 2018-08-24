package silly.myself.bean

data class User(// 数据库
        var _id: Int = 0,
        var account: String = "",
        var password: String = "",
        var create_time: String = ""){
    override fun toString(): String {
        return "{\"_id\":$_id,\"account\":\"$account\",\"create_time\":\"$create_time\"}"
    }
}