package cn.sangluo.baseserver.mapper;

import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface CommonMapper {
    /**
     * 自定义批量保存数据
     *
     * @param tableName  表名
     * @param records    数据集合
     * @return 保存数据数目
     **/
    int batchSaveDynamic(@Param("tableName")String tableName, @Param("records") List<Map<String,Object>> records);


}
