package com.linkage.pcenter.common.component;

import com.linkage.pcenter.common.domain.MonitorNumber;
import com.linkage.pcenter.common.domain.OrgMessage;
import com.linkage.pcenter.common.domain.SMSMessageIF;
import com.linkage.pcenter.common.domain.TiMTMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.incrementer.OracleSequenceMaxValueIncrementer;
import org.springframework.stereotype.Component;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.Properties;


/**
 * Created by IntelliJ IDEA.
 * User: LiuYang
 * Date: 12-1-12
 * Time: 上午10:29
 */
@Component
public class MessageSend {
    final Logger logger = LoggerFactory.getLogger(MessageSend.class);
    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Autowired
    private Properties applicationProps;
    @Autowired
    private OracleSequenceMaxValueIncrementer smsSeq;

    /**
     * 发送短信
     *
     * @param message 消息对象
     * @return 返回影响的行数，0为未插入
     */
    public int sendMessage(SMSMessageIF message) {
        String userTable = applicationProps.getProperty("xxtdbuser");
        String sql = "";
        Object[] o = null;
        if (message != null) {
            if (message instanceof TiMTMessage) {
                //直接王TI_MT插入的接口
				if(validMessage(message)){
					TiMTMessage msg = (TiMTMessage) message;
					//直接插TI_MT
					sql = "INSERT INTO "+userTable+".list_timt (";
					sql += "GATEWAYID," +
							"USERTABLENAME," +
							"DSTID," +
							"SRCID," +
							"FEEID," +
							"MSGCONTENT," +
							"SERVICEID," +
							"COMMITTIME," +
							"SENDER_USERNAME," +
							"RECEIVER_USERNAME," +
							"TCID," +
							"SYSFUNCTIONID," +
							"SENDER_ID," +
							"RECEIVER_ID," +
							"SYSMESSAGETYPE) " +
							"VALUES(?,?,?,?,?,?,?,SYSDATE,?,?,?,?,?,?,?)";		
					o = new Object[]{
							"1",
					         "HELP",
					         msg.getDestuserdn(),
					         "",
					         msg.getDestuserdn(),
					         msg.getMessagecontent(),
					         "-SHQQ1MF",
					         " ",
					         " ",
					         "0",
					         "0",
					         "0",
					         "0",
					         "0"	
					};
				}
            } else if (message instanceof OrgMessage) {
                if (validMessage(message)) {
                    OrgMessage msg = (OrgMessage) message;
                    //插LIST_SENDBOX_TMP
                    sql = "INSERT INTO " + userTable + ".LIST_SENDBOX_TMP(";
                    sql += "MESSAGEID," +
                            "MESSAGECONTENT," +
                            "PRESENDTIME," +
                            "MESSAGEIDREPLY," +
                            "SYSFUNCTIONID," +
                            "SMSMESSAGETYPE," +
                            "AUDITINGSTATE," +
                            "SENDUSERID," +
                            "SENDUSERNAME," +
                            "SENDUSERORGID," +
                            "SENDUSERORGTYPE," +
                            "SENDUSERORGNAME," +
                            "LIST_SENDBOX_DESTTYPE," +
                            "DESTUSERID," +
                            "DESTUSERNAME," +
                            "DESTUSERDN," +
                            "DESTORGID," +
                            "DESTORGTYPE," +
                            "DESTORGNAME," +
                            "SENDIP," +
                            "ID," +
                            "SENDUSER_CITYID," +
                            "SENDUSER_AGENTL1ID," +
                            "SENDUSER_AGENTL2ID," +
                            "SENDUSER_DJID," +
                            "SENDUSER_SCHOOLID," +
                            "SENDUSER_COUNTYID," +
                            "SENDUSER_CLASSID," +
                            "SRCID)";
                    sql += " VALUES(?,?,TO_DATE(?,'yyyy-MM-dd HH24:mi:ss'),?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
                    if (msg.getMessageid() == 0) {
                        long smsId = smsSeq.nextLongValue();
                        msg.setMessageid(smsId);
                    }
                    o = new Object[]{msg.getMessageid(),
                            msg.getMessagecontent(),
                            msg.getPresendtime(),
                            msg.getMessagereply(),
                            msg.getSysfunctionid(),
                            msg.getSmsmessagetype(),
                            msg.getAuditingstate(),
                            msg.getSenduserid(),
                            msg.getSendusername(),
                            msg.getSenduserorgid(),
                            msg.getSenduserorgtype(),
                            msg.getSenduserorgname(),
                            msg.getList_sendbox_desttype(),
                            msg.getDestuserid(),
                            msg.getDestusername(),
                            msg.getDestuserdn(),
                            msg.getDestorgid(),
                            msg.getDestorgtype(),
                            msg.getDestorgname(),
                            msg.getSendIP(),
                            msg.getMessageid(),
                            msg.getSenduser_cityid(),
                            msg.getSenduser_agentl1id(),
                            msg.getSenduser_agentl2id(),
                            msg.getSenduser_djid(),
                            msg.getSenduser_schoolid(),
                            msg.getSenduser_countyid(),
                            msg.getSenduser_classid(),
                            msg.getSrcid()
                    };
                }
            }
            logger.info(sql.replaceAll("\\?", "{}"), o);
            return jdbcTemplate.update(sql, o);
        }
        return 0;
    }

    /**
     * 批量发送短信
     *
     * @param messages 消息对象
     * @return 返回影响的行数，0为未插入
     */
    public int[] batchSendMessage(final List<SMSMessageIF> messages) {
        String userTable = applicationProps.getProperty("xxtdbuser");
        String sql;
        if (messages != null) {
            long smsId = smsSeq.nextLongValue();
            //插LIST_SENDBOX_TMP
            sql = "INSERT INTO " + userTable + ".LIST_SENDBOX_TMP(";
            sql += "MESSAGEID," +
                    "MESSAGECONTENT," +
                    "PRESENDTIME," +
                    "MESSAGEIDREPLY," +
                    "SYSFUNCTIONID," +
                    "SMSMESSAGETYPE," +
                    "AUDITINGSTATE," +
                    "SENDUSERID," +
                    "SENDUSERNAME," +
                    "SENDUSERORGID," +
                    "SENDUSERORGTYPE," +
                    "SENDUSERORGNAME," +
                    "LIST_SENDBOX_DESTTYPE," +
                    "DESTUSERID," +
                    "DESTUSERNAME," +
                    "DESTUSERDN," +
                    "DESTORGID," +
                    "DESTORGTYPE," +
                    "DESTORGNAME," +
                    "SENDIP," +
                    "ID," +
                    "SENDUSER_CITYID," +
                    "SENDUSER_AGENTL1ID," +
                    "SENDUSER_AGENTL2ID," +
                    "SENDUSER_DJID," +
                    "SENDUSER_SCHOOLID," +
                    "SENDUSER_COUNTYID," +
                    "SENDUSER_CLASSID," +
                    "SRCID)";
            sql += " VALUES(" + smsId + ",?,TO_DATE(?,'yyyy-MM-dd HH24:mi:ss'),?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?," + userTable + ".NORMALSERIAL.NEXTVAL,?,?,?,?,?,?,?,?)";


            return jdbcTemplate.batchUpdate(sql, new BatchPreparedStatementSetter() {

                public int getBatchSize() {
                    return messages.size();
                }

                public void setValues(PreparedStatement ps, int i)
                        throws SQLException {
                    OrgMessage msg = (OrgMessage) messages.get(i);
                    ps.setString(1, msg.getMessagecontent());
                    ps.setString(2, msg.getPresendtime());
                    ps.setLong(3, msg.getMessagereply());
                    ps.setInt(4, msg.getSysfunctionid());
                    ps.setLong(5, msg.getSmsmessagetype());
                    ps.setLong(6, msg.getAuditingstate());
                    ps.setLong(7, msg.getSenduserid());
                    ps.setString(8, msg.getSendusername());
                    ps.setLong(9, msg.getSenduserorgid());
                    ps.setInt(10, msg.getSenduserorgtype());
                    ps.setString(11, msg.getSenduserorgname());
                    ps.setInt(12, msg.getList_sendbox_desttype());
                    ps.setLong(13, msg.getDestuserid());
                    ps.setString(14, msg.getDestusername());
                    ps.setString(15, msg.getDestuserdn());
                    ps.setLong(16, msg.getDestorgid());
                    ps.setInt(17, msg.getDestorgtype());
                    ps.setString(18, msg.getDestorgname());
                    ps.setString(19, msg.getSendIP());

                    ps.setLong(20, msg.getSenduser_cityid());
                    ps.setLong(21, msg.getSenduser_agentl1id());
                    ps.setLong(22, msg.getSenduser_agentl2id());
                    ps.setLong(23, msg.getSenduser_djid());

                    ps.setLong(24, msg.getSenduser_schoolid());
                    ps.setLong(25, msg.getSenduser_countyid());
                    ps.setLong(26, msg.getSenduser_classid());
                    ps.setString(27, msg.getSrcid());
                }

            });
        }
        return new int[]{};
    }

    /**
     * 批量发送短信
     *
     * @param messages 消息对象
     * @param smsId    批量插入的ID
     * @return 返回影响的行数，0为未插入
     */
    public int[] batchSendMessage(final List<SMSMessageIF> messages, long smsId) {
        String userTable = applicationProps.getProperty("xxtdbuser");
        String sql = "";
        if (messages != null) {
            //插LIST_SENDBOX_TMP
            sql = "INSERT INTO " + userTable + ".LIST_SENDBOX_TMP(";
            sql += "MESSAGEID," +
                    "MESSAGECONTENT," +
                    "PRESENDTIME," +
                    "MESSAGEIDREPLY," +
                    "SYSFUNCTIONID," +
                    "SMSMESSAGETYPE," +
                    "AUDITINGSTATE," +
                    "SENDUSERID," +
                    "SENDUSERNAME," +
                    "SENDUSERORGID," +
                    "SENDUSERORGTYPE," +
                    "SENDUSERORGNAME," +
                    "LIST_SENDBOX_DESTTYPE," +
                    "DESTUSERID," +
                    "DESTUSERNAME," +
                    "DESTUSERDN," +
                    "DESTORGID," +
                    "DESTORGTYPE," +
                    "DESTORGNAME," +
                    "SENDIP," +
                    "ID," +
                    "SENDUSER_CITYID," +
                    "SENDUSER_AGENTL1ID," +
                    "SENDUSER_AGENTL2ID," +
                    "SENDUSER_DJID," +
                    "SENDUSER_SCHOOLID," +
                    "SENDUSER_COUNTYID," +
                    "SENDUSER_CLASSID," +
                    "SRCID)";
            sql += " VALUES(" + smsId + ",?,TO_DATE(?,'yyyy-MM-dd HH24:mi:ss'),?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?," + userTable + ".NORMALSERIAL.NEXTVAL,?,?,?,?,?,?,?,?)";


            return jdbcTemplate.batchUpdate(sql, new BatchPreparedStatementSetter() {

                public int getBatchSize() {
                    return messages.size();
                }

                public void setValues(PreparedStatement ps, int i)
                        throws SQLException {
                    OrgMessage msg = (OrgMessage) messages.get(i);
                    ps.setString(1, msg.getMessagecontent());
                    ps.setString(2, msg.getPresendtime());
                    ps.setLong(3, msg.getMessagereply());
                    ps.setInt(4, msg.getSysfunctionid());
                    ps.setLong(5, msg.getSmsmessagetype());
                    ps.setLong(6, msg.getAuditingstate());
                    ps.setLong(7, msg.getSenduserid());
                    ps.setString(8, msg.getSendusername());
                    ps.setLong(9, msg.getSenduserorgid());
                    ps.setInt(10, msg.getSenduserorgtype());
                    ps.setString(11, msg.getSenduserorgname());
                    ps.setInt(12, msg.getList_sendbox_desttype());
                    ps.setLong(13, msg.getDestuserid());
                    ps.setString(14, msg.getDestusername());
                    ps.setString(15, msg.getDestuserdn());
                    ps.setLong(16, msg.getDestorgid());
                    ps.setInt(17, msg.getDestorgtype());
                    ps.setString(18, msg.getDestorgname());
                    ps.setString(19, msg.getSendIP());

                    ps.setLong(20, msg.getSenduser_cityid());
                    ps.setLong(21, msg.getSenduser_agentl1id());
                    ps.setLong(22, msg.getSenduser_agentl2id());
                    ps.setLong(23, msg.getSenduser_djid());

                    ps.setLong(24, msg.getSenduser_schoolid());
                    ps.setLong(25, msg.getSenduser_countyid());
                    ps.setLong(26, msg.getSenduser_classid());
                    ps.setString(27, msg.getSrcid());
                }

            });
        }
        return new int[]{};
    }

    /*
      * 验证信息对象
      */
    private boolean validMessage(SMSMessageIF message) {
        if (message instanceof TiMTMessage) {
        	TiMTMessage msg = (TiMTMessage) message;
             if (msg.getMessagecontent() == null || msg.getMessagecontent().equals("")) {
                 return false;
             }              
			 return true;
        }
        if (message instanceof OrgMessage) {
            OrgMessage msg = (OrgMessage) message;
            if (msg.getMessagecontent() == null || msg.getMessagecontent().equals("")) {
                return false;
            } else if (!msg.checkPresendDate()) {
                return false;
            } else if (msg.getSenduserid() == 0) {
                return false;
            } else if (msg.getSendusername() == null) {
                return false;
            } else if (msg.getSenduserorgid() == 0) {
                return false;
            } else if (msg.getSenduserorgtype() != 7 && msg.getSenduserorgtype() != 8) {
                return false;
            } else if (msg.getSendIP() == null) {
                return false;
            } else if (msg.getList_sendbox_desttype() == 0) {
                return false;
            } else if (msg.getList_sendbox_desttype() == 1) {
                if (msg.getDestuserid() == 0) {
                    return false;
                }
            } else if (msg.getList_sendbox_desttype() == 2) {
                if (msg.getDestuserdn().equals(" ")) {
                    return false;
                }
            } else if (msg.getList_sendbox_desttype() == 3) {
                if (msg.getDestorgid() == 0 || msg.getDestorgtype() == 0 || (msg.getDestorgtype() != 7 && msg.getDestorgtype() != 8)) {
                    return false;
                }
            } else {
                return false;
            }
            return true;
        }
        return false;
    }


    public int monitorNumber(MonitorNumber monitorNum) {
        String userTable = applicationProps.getProperty("xxtdbuser");
        String sql;
        Object[] o;
        if (monitorNum != null) {
            //插MONITORFORTEAMSG
            sql = "INSERT INTO " + userTable + ".MONITORFORTEAMSG(";
            sql += "MESSAGEID," +
                    "MESSAGECONTENT," +
                    "COMMITTIME," +
                    "PRESENDTIME," +
                    "SENDDNS)";
            sql += " VALUES(?,?,TO_DATE(?,'yyyy-MM-dd HH24:mi:ss'),TO_DATE(?,'yyyy-MM-dd HH24:mi:ss'),?)";
            if (monitorNum.getMessageid() == 0) {
                monitorNum.setMessageid(smsSeq.nextLongValue());
            }
            o = new Object[]{monitorNum.getMessageid(), monitorNum.getMessageContent(), monitorNum.getCommitTime(), monitorNum.getPreSendTime(), monitorNum.getSenddns()};
            logger.info(sql.replaceAll("\\?", "{}"), o);
            return jdbcTemplate.update(sql, o);
        }
        return 0;
    }

    public static void main(String[] args) {
        long startTime = System.currentTimeMillis();
        MessageSend ms = new MessageSend();
        OrgMessage message = new OrgMessage();
        message.setMessagecontent("   ");
        message.setPresendtime("2011-09-20 11:11:11");
        message.setSenduserid(100);
        message.setSendusername("aaa");
        message.setSenduserorgid(2222);
        message.setSenduserorgtype(7);
        message.setSendIP("127.0.0.1");
        message.setList_sendbox_desttype(3);
        message.setDestorgid(11);
        message.setDestorgtype(8);
        boolean bol = ms.validMessage(message);
        long endTime = System.currentTimeMillis();
        System.out.println("处理时间:" + (endTime - startTime) + "毫秒");
        System.out.println("处理结果:" + bol);

    }
}