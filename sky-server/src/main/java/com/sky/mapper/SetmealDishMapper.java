package com.sky.mapper;

import com.sky.constant.StatusConstant;
import com.sky.entity.SetmealDish;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface SetmealDishMapper {

    /**
     * 根据菜品id查询对应套餐id
     * @param ids
     * @return
     */
    List<Long> getSetmealIdsByDishIds(List<Long> ids);

    /**
     * 根据id回显套餐数据
     * @param id
     * @return
     */
    List<SetmealDish> getById(Long id);

    /**
     * 根据套餐id删除套餐菜品关系
     * @param id
     */
    @Delete("delete from setmeal_dish where setmeal_id = #{id}")
    void deleteBySetmealId(Long id);

    /**
     * 删除套餐菜品关系
     * @param ids
     */
    void delete(List<Long> ids);

    /**
     * 当菜品停售时，将包含其的套餐一并停售
     * @param id 菜品id
     */
    @Update("update setmeal " +
            "set status = #{disableStatus} " +
            "where id in (" +
            "   select setmeal_id from setmeal_dish where dish_id = #{id}" +
            ")")
    void change(Long id, Integer disableStatus);
}
