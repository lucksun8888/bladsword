package com.param;

public class Opitons {
	//状态 0-普通 1-健康 2-中毒 8-成仙 9-死亡 
	public final static String STAT_NORMOR = "0";
	public final static String STAT_WELL = "1";
	public final static String STAT_toxicosis = "2";
	public final static String STAT_GOD = "8";
	public final static String STAT_BAD = "9";
	
	//资质 1-良好 2-糟糕  9-仙人一般
	public final static String INTELLIGENCE_WELL = "1";
	public final static String INTELLIGENCE_LOW = "2";
	public final static String INTELLIGENCE_GOD = "9";
	
	// 事件类型 1-对话 2-战斗 3-获取道具 4-商店事件 5-被盗 6-偷窃
	public final static String EVENT_TYPE_TALK = "1";
	public final static String EVENT_TYPE_FIGHT = "2";
	public static final String EVENT_TYPE_GETITEM = "3";
	public final static String EVENT_TYPE_TASK = "R";
	
	
	// 技能类型 1-攻击 2-恢复 3-用毒 4-偷窃
	public final static String SKILL_TYPE_GJ = "1";
	public final static String SKILL_TYPE_HF = "2";
	public final static String SKILL_TYPE_YD = "3";
	public final static String SKILL_TYPE_TQ = "4";
	
	// 任务类型 1-主线不可重复 2-支线不可重复 3-支线可重复
	public final static String PTASK_TYPE_MAIN = "1";
	public final static String PTASK_TYPE_JOIN = "2";
	public final static String PTASK_TYPE_JOINS = "3";
	
	// 任务状态 0-未执行 1-已经执行 2-已经领取奖励
	public final static String TASK_FLAG_0 = "0";
	public final static String TASK_FLAG_1 = "1";
	public final static String TASK_FLAG_2 = "2";
	
	// 道具类型
	public final static String PITEM_TYPE_0= "0"; // 使用道具
	public final static String PITEM_TYPE_1= "1"; // 任务道具
	public final static String PITEM_TYPE_2= "2"; // 装备
	public final static String PITEM_TYPE_3= "3"; // 技能
	public final static String PITEM_TYPE_4= "4"; // 货币
	
	
	// 道具种类
	public final static String  PITEM_KIND_0 = "0"; // 恢复血量（按点数）
	public final static String  PITEM_KIND_1 = "1"; // 恢复血量（按比例）
	public final static String  PITEM_KIND_2 = "2"; // 恢复状态
	public final static String  PITEM_KIND_3 = "3"; // 提升功力（按点数）
	public final static String  PITEM_KIND_4 = "4"; // 提升功力（按比例）
	public final static String  PITEM_KIND_5 = "5"; // 提供攻击力（按点数）
	public final static String  PITEM_KIND_6 = "6"; // 提供攻击力（按比例）
	public final static String  PITEM_KIND_7 = "7"; // 提升防御力（按点数）
	public final static String  PITEM_KIND_8 = "8"; // 提升防御力 （按比例）
	
	// 解除状态类型
	public final static String STATE_R_0 = "0"; // 解毒
	public final static String STATE_R_1 = "1"; // 解内伤
	
}
