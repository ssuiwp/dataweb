package com.swp.dataweb.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.swp.dataweb.entity.Form;
import com.swp.dataweb.entity.FormItem;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface FormItemMapper extends BaseMapper<FormItem> {
    List<Long> getItemIds(Long formId);

    @Update("update form_item set used = 0 where form_id = #{form.id} and item_id = #{itemId}")
    int deleteRelation(@Param("form") Form form,
                        @Param("itemId")Long itemId);
}
