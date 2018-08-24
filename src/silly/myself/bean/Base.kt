package silly.myself.bean

data class SuccessMap(var msg: Int, var param: String, var data: Map<String, Any>)
data class SuccessList<T>(var msg: Int, var param: String, var data: List<T>)
data class SuccessString(var msg: Int, var param: String, var data: String)
data class FailString(var msg: Int, var param: String)