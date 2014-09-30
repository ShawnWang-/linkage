package com.linkage.pcenter.common.domain;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * Created by IntelliJ IDEA.
 * User: LiuYang
 * Date: 12-1-12
 * Time: 上午10:29
 */
public class TiMTMessage implements SMSMessageIF {
	
	/**
	 * 短信ID，批量时可相同
	 */
	private long messageid;
	/**
	 * 消息内容(必填)
	 */
	private String	messagecontent;
	/**
	 * 预约时间,时间格式(yyyy-MM-dd HH:mm:ss)
	 */
	private String	presendtime			= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
	/**
	 * 回复的ID
	 */
	private long	messagereply		= 0;
	/**
	 * 模块ID
	 */
	private int		sysfunctionid		= 9;
	/**
	 * 消息类型
	 * 校讯通的定义：0:帮助短信,1:找回密码,2:验证码,3:办公短信,4:自写短信,5:学生评语,6:考试成绩,7:家长通知,8:短信代发,
	 * 9:平安短信
	 * ,10:代理商接口MT,11:站内消息,12:回复信息,13:定时提醒,14:作业,15:家长上行,16:回复家长上行,17:留守儿童,
	 * 18:二次确认信息,19:校讯通专刊,20:wap push短信
	 * 互动空间定义：1:找回密码，2:验证码，7:公告，14：作业，4：自写短信，6：考试成绩
	 * ，13：定时提醒，12：回复信息，15：家长上行，16：回复家长上行
	 */
	private long	smsmessagetype;
	/**
	 * 审核状态，0：不需要审核，1需要审核
	 */
	private int auditingstate = 0;
	/**
	 * 发送人ID(必填)
	 */
	private long	senduserid;
	/**
	 * 发送人姓名(必填)
	 */
	private String	sendusername;
	/**
	 * 发送人组织ID，教师填学校组织，家长填班级组织(必填)
	 */
	private long	senduserorgid;
	/**
	 * 发送人组织类型(必填) 7：学校，8：班级
	 */
	private int		senduserorgtype;
	/**
	 * 发送人组织名称
	 */
	private String	senduserorgname;
	/**
	 * 目标组织类型：1：个人：向DestUserID发,2:对号码发送，向DestUserDN发；3：对组织发，向DestOrgID发(必填重要)
	 */
	private int		list_sendbox_desttype;
	/**
	 * 目标用户ID，如果对号码对组织发送则为0。 目标用户ID,对个人发送（即list_sendbox_desttype = 1时）有效。
	 */
	private long	destuserid			= 0;
	/**
	 * 目标用户名称，如果对组织发送则填为空格
	 */
	private String	destusername		= " ";
	/**
	 * 对号码发送时（即list_sendbox_desttype = 2时）起作用，否则填为空格
	 */
	private String	destuserdn			= " ";
	/**
	 * 目标组织ID，对组织发送（即list_sendbox_desttype = 3时）有效。否则填为0
	 */
	private long	destorgid			= 0;
	/**
	 * 目标组织类型（list_sendbox_desttype = 3时必填7或8） 7：学校；8：班级
	 */
	private int		destorgtype			= 0;
	/**
	 * 目标组织名称（list_sendbox_desttype = 3时必填）
	 */
	private String	destorgname			= " ";
	/**
	 * 发送者IP（必填）
	 */
	private String	sendIP;
	/**
	 * 发送人地市ID（必填）
	 */
	private long	senduser_cityid		= 0;
	/**
	 * 发送人一级代理商ID，如果没有填为0（必填）
	 */
	private long	senduser_agentl1id	= 0;
	/**
	 * 发送人二级代理商ID，如果没有填为0（必填）
	 */
	private long	senduser_agentl2id	= 0;
	/**
	 * 发送人代接ID，如果没有填为0（必填）
	 */
	private long	senduser_djid		= 0;
	/**
	 * 发送人学校ID（必填）
	 */
	private long	senduser_schoolid	= 0;
	/**
	 * 发送人区县ID（必填）
	 */
	private long	senduser_countyid	= 0;
	/**
	 * 发送人班级ID,家长时填为家长所在班级id，教师时为0（必填）
	 */
	private long	senduser_classid	= 0;
	/**
	 * 源号码(短号)
	 */
	private String	srcid;
	public long getDestorgid() {
		return destorgid;
	}
	public void setDestorgid(long destorgid) {
		this.destorgid = destorgid;
	}
	public String getDestorgname() {
		return destorgname;
	}
	public void setDestorgname(String destorgname) {
		this.destorgname = destorgname;
	}
	public int getDestorgtype() {
		return destorgtype;
	}
	public void setDestorgtype(int destorgtype) {
		this.destorgtype = destorgtype;
	}
	public String getDestuserdn() {
		return destuserdn;
	}
	public void setDestuserdn(String destuserdn) {
		this.destuserdn = destuserdn;
	}
	public long getDestuserid() {
		return destuserid;
	}
	public void setDestuserid(long destuserid) {
		this.destuserid = destuserid;
	}
	public String getDestusername() {
		return destusername;
	}
	public void setDestusername(String destusername) {
		this.destusername = destusername;
	}
	public int getList_sendbox_desttype() {
		return list_sendbox_desttype;
	}
	public void setList_sendbox_desttype(int list_sendbox_desttype) {
		this.list_sendbox_desttype = list_sendbox_desttype;
	}
	public String getMessagecontent() {
		return messagecontent;
	}
	public void setMessagecontent(String messagecontent) {
		this.messagecontent = messagecontent;
	}
	public long getMessagereply() {
		return messagereply;
	}
	public void setMessagereply(long messagereply) {
		this.messagereply = messagereply;
	}
	public String getPresendtime() {
		return presendtime;
	}
	public void setPresendtime(String presendtime) {
		this.presendtime = presendtime;
	}
	public String getSendIP() {
		return sendIP;
	}
	public void setSendIP(String sendIP) {
		this.sendIP = sendIP;
	}
	public long getSenduser_agentl1id() {
		return senduser_agentl1id;
	}
	public void setSenduser_agentl1id(long senduser_agentl1id) {
		this.senduser_agentl1id = senduser_agentl1id;
	}
	public long getSenduser_agentl2id() {
		return senduser_agentl2id;
	}
	public void setSenduser_agentl2id(long senduser_agentl2id) {
		this.senduser_agentl2id = senduser_agentl2id;
	}
	public long getSenduser_cityid() {
		return senduser_cityid;
	}
	public void setSenduser_cityid(long senduser_cityid) {
		this.senduser_cityid = senduser_cityid;
	}
	public long getSenduser_classid() {
		return senduser_classid;
	}
	public void setSenduser_classid(long senduser_classid) {
		this.senduser_classid = senduser_classid;
	}
	public long getSenduser_countyid() {
		return senduser_countyid;
	}
	public void setSenduser_countyid(long senduser_countyid) {
		this.senduser_countyid = senduser_countyid;
	}
	public long getSenduser_djid() {
		return senduser_djid;
	}
	public void setSenduser_djid(long senduser_djid) {
		this.senduser_djid = senduser_djid;
	}
	public long getSenduser_schoolid() {
		return senduser_schoolid;
	}
	public void setSenduser_schoolid(long senduser_schoolid) {
		this.senduser_schoolid = senduser_schoolid;
	}
	public long getSenduserid() {
		return senduserid;
	}
	public void setSenduserid(long senduserid) {
		this.senduserid = senduserid;
	}
	public String getSendusername() {
		return sendusername;
	}
	public void setSendusername(String sendusername) {
		this.sendusername = sendusername;
	}
	public long getSenduserorgid() {
		return senduserorgid;
	}
	public void setSenduserorgid(long senduserorgid) {
		this.senduserorgid = senduserorgid;
	}
	public String getSenduserorgname() {
		return senduserorgname;
	}
	public void setSenduserorgname(String senduserorgname) {
		this.senduserorgname = senduserorgname;
	}
	public int getSenduserorgtype() {
		return senduserorgtype;
	}
	public void setSenduserorgtype(int senduserorgtype) {
		this.senduserorgtype = senduserorgtype;
	}
	public long getSmsmessagetype() {
		return smsmessagetype;
	}
	public void setSmsmessagetype(long smsmessagetype) {
		this.smsmessagetype = smsmessagetype;
	}
	public String getSrcid() {
		return srcid;
	}
	public void setSrcid(String srcid) {
		this.srcid = srcid;
	}
	public int getSysfunctionid() {
		return sysfunctionid;
	}
	public void setSysfunctionid(int sysfunctionid) {
		this.sysfunctionid = sysfunctionid;
	}
	/**
	 * 验证预约时间是否符合时间yyyy-MM-DD HH:mm:ss格式
	 * 
	 * @return true：符合，false：不符合
	 */
	public boolean checkPresendDate() {
		String s = new String(presendtime);
		DateFormat f = new SimpleDateFormat("yyyy-MM-DD HH:mm:ss");
		try {
			Date d = (Date) f.parse(s);
			Calendar c = new GregorianCalendar();
			c.setTime(d);
			return true;
		} catch (Exception ex) {
			ex.printStackTrace();
			return false;
		}
	}
	/**
	 * 添加发件人信息
	 * 
	 * @param user
	 *            用户对象
	 */
	private void addSendUser(User user) {
//		this.auditingstate = user.getNeedauditing();
//		this.senduserid = user.getUserid();
//		this.sendusername = user.getUsername();
//		this.senduserorgid = user.getOrgid();
//		if (user.getUsertype() == 0) {
//			this.senduserorgtype = 8;
//			this.senduser_classid = user.getCurrentClassId();
//			this.senduser_schoolid = user.getCurrentStudent().getSchoolid();
//		} else if (user.getUsertype() == 1 || user.getUsertype() == 2) {
//			this.senduserorgtype = 7;
//			this.senduser_schoolid = user.getOrgid();
//		}
//		this.senduserorgname = user.getOrgname();
//		this.senduser_cityid = user.getCityid();
//		this.senduser_agentl1id = user.getAgentl1id();
//		this.senduser_agentl2id = user.getAgentl2id();
//		this.senduser_djid = user.getDjid();
//		this.senduser_countyid = user.getCountyid();
//		this.sendIP = user.getLoginIP();
	}
	public TiMTMessage() {
	}
	/**
	 * 发送短信构造函数，自动添加发送人
	 * 
	 * @param user
	 *            发送人对象
	 */
	public TiMTMessage(User user) {
		addSendUser(user);
	}
	/**
	 * 发送短信构造函数，自动添加发送人及收信对象信息.
	 * 如为1（对个人发），必须对destuserid（收信人ID）和destusername（收信人姓名）赋值。
	 * 如为2（对号码发）：必须对destuserdn（收信人手机号码）赋值。
	 * 如为3（对组织发）：必须对destorgid（组织ID），destorgtype
	 * （组织类型,7：学校；8：班级）和destorgname（组织名称）赋值。
	 * 选填内容包括：smsmessagetype（消息内容）,presendtime(预约发送时间，默认即时发送)，
	 * 
	 * @param user
	 *            发送人对象
	 * @param sendType
	 *            1：个人,2:对号码发送,3：对组织发
	 */
	public TiMTMessage(User user, int sendType) {
		addSendUser(user);
		this.list_sendbox_desttype = sendType;
	}
	public int getAuditingstate() {
		return auditingstate;
	}
	public void setAuditingstate(int auditingstate) {
		this.auditingstate = auditingstate;
	}
	public long getMessageid() {
		return messageid;
	}
	public void setMessageid(long messageid) {
		this.messageid = messageid;
	}
}
