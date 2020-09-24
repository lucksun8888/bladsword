package main.task.impl;

import com.dbservice.impl.DbServiceImpl;
import com.result.impl.Rslist;

import main.game.LogIn;
import main.task.Task;

/**
 * 任务功能，创建任务，更新任务状态等
 * @author dev
 *
 */
public class TaskImpl implements Task{
	DbServiceImpl DBS;
	LogIn LOGIN;
	Rslist TASKLIST;
	
	public TaskImpl(LogIn login,DbServiceImpl dbs){
		this.DBS = dbs;
		this.LOGIN = login; 
	}
	
	public void loadTask() throws Exception{
		StringBuffer sql = new StringBuffer();
		sql.append("select * from task where user_Id ='"+ LOGIN.getLoginfo().getValue("user_id") +"' order by task_id,task_num,task_flag desc");
		TASKLIST = DBS.excuteQuery(sql.toString());
	}
	
	public Rslist getMainRl(){
		Rslist rs = new Rslist();
		for(int i =0;i<TASKLIST.getLength();i++){
			if("1".equals(TASKLIST.get(i).getValue("task_num"))){
				rs.add(TASKLIST.get(i));
			}
		}
		
		return rs;
	}
	
	public Rslist getJoinRl(String task_id){
		Rslist rs = new Rslist();
		for(int i =0;i<TASKLIST.getLength();i++){
			if(task_id.equals(TASKLIST.get(i).getValue("task_id"))){
				rs.add(TASKLIST.get(i));
			}
		}
		return rs;
	}
	
	/**
	 * 将任务获取到本用户下
	 * @param ptask_id
	 * @throws Exception 
	 */
	public void createTask(String ptask_id) throws Exception {
		// 检查任务是否允许重复进行，如果不允许或未完成则提示否则创建
		StringBuffer sql = new StringBuffer();
		sql.append("select case when (select count(1) from task where task_flag = '0' and task_id = '" + ptask_id
				+ "') > 0 or (select task_type from ptask where ptask_id = '" + ptask_id
				+ "') != '3' then 'n' else 'y' end as result from dual");
		if("n".equals(DBS.excuteQuery(sql.toString()).get(0).getValue("result"))){
			System.out.println("任务未完成，或不可重复申请！");
			return;
		}
		
		sql = new StringBuffer();
		sql.append("insert into task (user_id,task_id,task_num,task_name,task_note,task_event_id,task_flag)"
				+ "select '" + LOGIN.getLoginfo().getValue("user_id")
				+ "' as user_id,ptask_id,ptask_num,ptask_name,ptask_note,ptask_event_id,'0' as task_flag"
				+ "from ptask where ptask_id = '" + ptask_id + "'");
		DBS.excuteSql(sql.toString());
	}

	/**
	 * 更新任务状态
	 * @param event_id
	 * @throws Exception 
	 */
	public void update(String event_id,String task_flag) throws Exception {
		StringBuffer sql = new StringBuffer();
		sql.append("update task set task_flag = '"+task_flag+"' where event_id = '"+ event_id + "'");
		DBS.excuteSql(sql.toString());
	}
	
	
}
