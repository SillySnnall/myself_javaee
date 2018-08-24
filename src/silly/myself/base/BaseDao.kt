package silly.myself.base

import org.apache.commons.dbutils.QueryRunner
import silly.myself.utils.C3P0Utils

open class BaseDao {

   fun getQueryRunner(): QueryRunner {
       return QueryRunner(C3P0Utils.getDataSource())
   }
}