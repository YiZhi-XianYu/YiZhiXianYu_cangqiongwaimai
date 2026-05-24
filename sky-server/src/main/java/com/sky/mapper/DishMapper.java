package com.sky.mapper;

import com.github.pagehelper.Page;
import com.sky.annotation.AutoFill;
import com.sky.dto.DishPageQueryDTO;
import com.sky.entity.Dish;
import com.sky.enumeration.OperationType;
import com.sky.vo.DishVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface DishMapper {

    /**
     * 根据分类id查询菜品数量
     * @param categoryId
     * @return
     */
    @Select("select count(id) from dish where category_id = #{categoryId}")
    Integer countByCategoryId(Long categoryId);

    /**
     * 插入菜品数据
     * @param dish
     */
    @AutoFill(value = OperationType.INSERT)
    void insert(Dish dish);

    /**
     * 菜品分页查询
     * @param dishPageQueryDTO
     * @return
     */
    Page<DishVO> pageQuery(DishPageQueryDTO dishPageQueryDTO);

    /**
     * 根据id查询菜品
     * @param id
     * @return
     */
    @Select("select * from dish where  id = #{id}")
    Dish getById(Long id);

    /**
     * 批量删除菜品
     * @param ids
     */
    void deleteById(List<Long> ids);

    /**
     * 修改菜品基本信息
     * @param dish
     */
    @AutoFill(value = OperationType.UPDATE)
    void update(Dish dish);

    /**
     * 动态条件查询菜品
     * @param dish
     * @return
     */
    List<Dish> list(Dish dish);

    /**
     * 设置菜品起售停售状态
     * @param status
     * @param id
     */
    @Update("update dish set status = #{status} where id = #{id}")
    void change(int status,Long id);

    /**
     * 根据套餐id查询菜品
     * @param id
     * @return
     */
    @Select("select dish.* from dish left outer join setmeal_dish on dish.id = setmeal_dish.dish_id where setmeal_dish.setmeal_id = #{id}")
    List<Dish> getBySetmealId(Long id);
}
