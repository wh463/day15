package com.xiaoshu.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.github.pagehelper.util.StringUtil;
import com.xiaoshu.dao.GoodsMapper;
import com.xiaoshu.dao.GoodstypeMapper;
import com.xiaoshu.dao.UserMapper;
import com.xiaoshu.entity.Goods;
import com.xiaoshu.entity.GoodsVo;
import com.xiaoshu.entity.Goodstype;
import com.xiaoshu.entity.User;
import com.xiaoshu.entity.UserExample;
import com.xiaoshu.entity.UserExample.Criteria;

@Service
public class GoodsService {
    @Autowired
    GoodsMapper goodsMapper;
    @Autowired
    GoodstypeMapper goodstypeMapper;
	public PageInfo<GoodsVo> findPage(GoodsVo goodsVo, Integer pageNum, Integer pageSize) {
		// TODO Auto-generated method stub
		PageHelper.startPage(pageNum, pageSize);
		List<GoodsVo> list = goodsMapper.findList(goodsVo);
		return new PageInfo<>(list);
	}
   //取值时间的值
	public void addGoods(Goods goods) {
		goods.setCreatetime(new Date());
		goodsMapper.insert(goods);
		
	}
    //去重
	public Goods findName(String name) {
		Goods param=new Goods();
		param.setName(name);
		return goodsMapper.selectOne(param);
		
	}
	
	public List<Goodstype>findGoodstype(){
		return goodstypeMapper.selectAll();//selectAll查询所有 //List里面的条件为附表        
																//这个方法只用于查询附表所有信息
		
	}
	public void updateGoods(Goods goods){
		goodsMapper.updateByPrimaryKeySelective(goods);
	}
	public void deleteGoods(Integer id){
		goodsMapper.deleteByPrimaryKey(id);
	}
}
