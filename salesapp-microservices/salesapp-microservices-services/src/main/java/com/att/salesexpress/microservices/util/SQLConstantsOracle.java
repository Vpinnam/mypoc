package com.att.salesexpress.microservices.util;

public interface SQLConstantsOracle {

	String sqlGetSalesHistoryDataByAccessTypeIndexWithinGroup20170607 = "select rankTable.* from ("
			+ "select countTable.*, "
			+ "dense_rank() over (order by countTable.NUMBER_OF_SALES desc) RNK "
			+ "from ("
			+ "select "
			+ "count(*) over (partition by sth.ACCESS_SPEED_ID,  sth.PORT_SPEED_ID, sth.BUNDLE_CD) as NUMBER_OF_SALES, "
			//+ "concat(ROUND(count(*) over () * 100/ totalTrans.cnt, 2), '%')  as MATCHING_ROW_PERCENTAGE, "
			+ "ROUND(count(*) over (partition by sth.ACCESS_SPEED_ID,  sth.PORT_SPEED_ID) * 100/ totalTrans.cnt, 2) as MATCHING_ROW_PERCENTAGE, "
			+ "rank() over ( partition by sth.ACCESS_SPEED_ID,  sth.PORT_SPEED_ID, sth.BUNDLE_CD order by sth.SITE_ID ) indexWithinGroup,"
			+ "sth.* "
			//+ "from SALES_TRANSACTION_HISTORY sth, (select count(*) cnt from SALES_TRANSACTION_HISTORY) totalTrans "
			+ "from SALES_TRANS_HISTORY_MIS_EXP sth, (select count(*) cnt from SALES_TRANS_HISTORY_MIS_EXP) totalTrans "
			+ "where sth.ACCESS_TYPE_ID = :ACCESS_TYPE_ID "
			+ ") countTable "
			+ ") rankTable "
			//+ "where rankTable.RNK = 1 and rownum <= :NUMBER_OF_ROWS"; 
			+ "where rankTable.indexWithinGroup < :INDEX_WITHIN_GROUP and rownum <= :NUMBER_OF_ROWS "
			+ "order by MATCHING_ROW_PERCENTAGE desc, MRC asc";
	
	String sqlGetSalesHistoryDataByAccessTypeIndexWithinGroupComplexWay = "select * from SALES_TRANS_HISTORY_MIS_EXP where ROWID in ("
			+ "select min(m.ROWID) from SALES_TRANS_HISTORY_MIS_EXP m , ("
			+ "select distinct DESIGN_NAME,ACCESS_SPEED_ID, MANAGED_ROUTER, MRC, NRC from ( "
			+ "select rankTable.DESIGN_NAME,rankTable.ACCESS_SPEED_ID, rankTable.MANAGED_ROUTER, rankTable.MRC, rankTable.NRC  from ("
			+ "    select countTable.*, "
			+ "        dense_rank() over (order by countTable.NUMBER_OF_SALES desc) RNK "
			+ "        from ("
			+ "        select"
			+ "        count(*) over (partition by sth.ACCESS_SPEED_ID,  sth.PORT_SPEED_ID, sth.BUNDLE_CD) as NUMBER_OF_SALES, "
			+ "        ROUND(count(*) over (partition by sth.ACCESS_SPEED_ID,  sth.PORT_SPEED_ID) * 100/ totalTrans.cnt, 2) as MATCHING_ROW_PERCENTAGE, "
			+ "        rank() over ( partition by sth.ACCESS_SPEED_ID,  sth.PORT_SPEED_ID, sth.BUNDLE_CD order by sth.SITE_ID ) indexWithinGroup, "
			+ "        sth.* "
			+ "        from SALES_TRANS_HISTORY_MIS_EXP sth, (select count(*) cnt from SALES_TRANS_HISTORY_MIS_EXP) totalTrans "
			+ "        where sth.ACCESS_TYPE_ID = 'ETHERNET' "
			+ "        ) countTable  ) rankTable "
			+ "    where rankTable.indexWithinGroup < 4 "
			+ "    order by MATCHING_ROW_PERCENTAGE desc, MRC asc"
			+ " ) x where rownum <= 25 "
			+ ") n "
			+ "where m.DESIGN_NAME=n.DESIGN_NAME and "
			+ "m.ACCESS_SPEED_ID=n.ACCESS_SPEED_ID and "
			+ "m.MANAGED_ROUTER=n.MANAGED_ROUTER and "
			+ "m.MRC=n.MRC and "
			+ "m.NRC=n.NRC "
			+ "group by  m.DESIGN_NAME, m.ACCESS_SPEED_ID, m.MANAGED_ROUTER, m.MRC, m.NRC "
			+ ")";

	String sqlGetSalesHistoryDataByAccessTypeIndexWithinGroup = "select * from ( "
			+ "select * from ( select "
			+ "row_number() over(partition by rankTable.DESIGN_NAME,rankTable.ACCESS_SPEED_ID, rankTable.MANAGED_ROUTER, rankTable.MRC, rankTable.NRC order by rankTable.SITE_ID) myrow, "
			+ "rankTable.* from ( "
			+ "        select countTable.*, "
			+ "            dense_rank() over (order by countTable.NUMBER_OF_SALES desc) RNK "
			+ "            from ( select "
			+ "            count(*) over (partition by sth.ACCESS_SPEED_ID,  sth.PORT_SPEED_ID, sth.BUNDLE_CD) as NUMBER_OF_SALES, "
			+ "            ROUND(count(*) over (partition by sth.ACCESS_SPEED_ID,  sth.PORT_SPEED_ID) * 100/ totalTrans.cnt, 2) as MATCHING_ROW_PERCENTAGE, "
			+ "            rank() over ( partition by sth.ACCESS_SPEED_ID,  sth.PORT_SPEED_ID, sth.BUNDLE_CD order by sth.SITE_ID ) indexWithinGroup, "
			+ "            sth.* "
			+ "            from SALES_TRANS_HISTORY_MIS_EXP sth, (select count(*) cnt from SALES_TRANS_HISTORY_MIS_EXP) totalTrans "
			+ "            where sth.ACCESS_TYPE_ID = :ACCESS_TYPE_ID "
			+ "            and sth.ACTIVE_YN = 'Y'"
			+ "            ) countTable "
			+ "            ) rankTable "
			+ "            where rankTable.indexWithinGroup < :INDEX_WITHIN_GROUP"
			+ "            order by MATCHING_ROW_PERCENTAGE desc, MRC asc"
			+ ") x where x.myrow=1 and rownum <= :NUMBER_OF_ROWS"
			+ " ) outerfilter where outerfilter.ACCESS_SPEED_ID in (50000, 150000)  and outerfilter.indexWithinGroup=1";

	String sqlGetSalesHistoryDataByAccessType = "select rankTable.* from ("
			+ "select countTable.*, "
			+ "dense_rank() over (order by countTable.NUMBER_OF_SALES desc) RNK "
			+ "from ("
			+ "select "
			+ "count(*) over (partition by sth.ACCESS_SPEED_ID,  sth.PORT_SPEED_ID, sth.BUNDLE_CD) as NUMBER_OF_SALES, "
			//+ "concat(ROUND(count(*) over () * 100/ totalTrans.cnt, 2), '%')  as MATCHING_ROW_PERCENTAGE, "
			+ "ROUND(count(*) over (partition by sth.ACCESS_SPEED_ID,  sth.PORT_SPEED_ID) * 100/ totalTrans.cnt, 2) as MATCHING_ROW_PERCENTAGE, "
			+ "rank() over ( partition by sth.ACCESS_SPEED_ID,  sth.PORT_SPEED_ID, sth.BUNDLE_CD order by sth.SITE_ID ) indexWithinGroup,"
			+ "sth.* "
			+ "from SALES_TRANS_HISTORY_MIS_EXP sth, (select count(*) cnt from SALES_TRANS_HISTORY_MIS_EXP) totalTrans "
			+ "where sth.ACCESS_TYPE_ID = :ACCESS_TYPE_ID "
			+ "and sth.ACTIVE_YN = 'Y'"
			+ ") countTable "
			+ ") rankTable "
			+ "where rankTable.indexWithinGroup = 1 and rownum <= :NUMBER_OF_ROWS "
			+ "order by MATCHING_ROW_PERCENTAGE desc, MRC asc";
	
	String sqlGetSalesHistoryDataByAccessTypeForOtherAccessType = "select rankTable.* from ("
			+ "select countTable.*, "
			+ "dense_rank() over (order by countTable.NUMBER_OF_SALES desc) RNK "
			+ "from ("
			+ "select "
			+ "count(*) over (partition by sth.ACCESS_SPEED_ID,  sth.PORT_SPEED_ID) as NUMBER_OF_SALES, "
			//+ "concat(ROUND(count(*) over () * 100/ totalTrans.cnt, 2), '%')  as MATCHING_ROW_PERCENTAGE, "
			+ "ROUND(count(*) over (partition by sth.ACCESS_SPEED_ID,  sth.PORT_SPEED_ID) * 100/ totalTrans.cnt, 2) as MATCHING_ROW_PERCENTAGE, "
			+ "rank() over ( partition by sth.ACCESS_SPEED_ID,  sth.PORT_SPEED_ID order by sth.SITE_ID ) indexWithinGroup,"
			+ "sth.* "
			//+ "from SALES_TRANSACTION_HISTORY sth, (select count(*) cnt from SALES_TRANSACTION_HISTORY) totalTrans "
			+ "from SALES_TRANS_HISTORY_MIS_EXP sth, (select count(*) cnt from SALES_TRANS_HISTORY_MIS_EXP) totalTrans "
			+ "where sth.ACCESS_TYPE_ID NOT IN ('ETHERNET', 'Private Line')"
			+ ") countTable "
			+ ") rankTable "
			//+ "where rankTable.RNK = 1 and rownum <= :NUMBER_OF_ROWS";
			+ "where rankTable.indexWithinGroup = 1 and rownum <= :NUMBER_OF_ROWS";

	String sqlGetSalesHistoryDataByAccessTypeAndAccessSpeed = "select rankTable.* from ("
			+ "select countTable.*, "
			+ "dense_rank() over (order by countTable.NUMBER_OF_SALES desc) RNK "
			+ "from ("
			+ "select "
			+ "count(*) over (partition by sth.PORT_SPEED_ID, sth.BUNDLE_CD) as NUMBER_OF_SALES, "
			//+ "concat(ROUND(count(*) over () * 100/ totalTrans.cnt, 2), '%')  as MATCHING_ROW_PERCENTAGE, "
			+ " ROUND(count(*) over (partition by sth.PORT_SPEED_ID) * 100/ totalTrans.cnt, 2)  as MATCHING_ROW_PERCENTAGE, "
			+ "rank() over ( partition by sth.PORT_SPEED_ID, sth.BUNDLE_CD order by sth.SITE_ID ) indexWithinGroup,"
			+ "sth.* "
			//+ "from SALES_TRANSACTION_HISTORY sth, (select count(*) cnt from SALES_TRANSACTION_HISTORY where ACCESS_TYPE_ID=:ACCESS_TYPE_ID) totalTrans "
			+ "from SALES_TRANS_HISTORY_MIS_EXP sth, (select count(*) cnt from SALES_TRANS_HISTORY_MIS_EXP where ACCESS_TYPE_ID=:ACCESS_TYPE_ID) totalTrans "
			+ "where sth.ACCESS_TYPE_ID = :ACCESS_TYPE_ID "
			+ "and   sth.ACCESS_SPEED_ID = :ACCESS_SPEED_ID "
			+ ") countTable "
			+ ") rankTable "
			//+ "where rankTable.RNK = 1 and rownum <= :NUMBER_OF_ROWS";
			+ "where rankTable.indexWithinGroup <= (:NUMBER_OF_ROWS)/2 and rownum <= :NUMBER_OF_ROWS";

	String sqlGetSalesHistoryDataByAccessTypeAndPortSpeedAndAccessSpeed = "select rankTable.* from ("
			+ "select countTable.*, "
			+ "dense_rank() over (order by countTable.NUMBER_OF_SALES desc) RNK "
			+ "from ("
			+ "select "
			+ "count(*) over (partition by sth.BUNDLE_CD) as NUMBER_OF_SALES, "
			+ "ROUND(count(*) over () * 100/ totalTrans.cnt, 2) as MATCHING_ROW_PERCENTAGE, "
			+ "rank() over (partition by sth.BUNDLE_CD order by sth.SITE_ID ) indexWithinGroup,"
			+ "sth.* "
			//+ "from SALES_TRANSACTION_HISTORY sth, ("
			+ "from SALES_TRANS_HISTORY_MIS_EXP sth, ("
			//+ "select count(*) cnt from SALES_TRANSACTION_HISTORY "
			+ "select count(*) cnt from SALES_TRANS_HISTORY_MIS_EXP "
			+ "where ACCESS_TYPE_ID=:ACCESS_TYPE_ID and ACCESS_SPEED_ID=:ACCESS_SPEED_ID) totalTrans "
			+ "where sth.ACCESS_TYPE_ID = :ACCESS_TYPE_ID "
			+ "and   sth.ACCESS_SPEED_ID = :ACCESS_SPEED_ID "
			+ "and   sth.PORT_SPEED_ID = :PORT_SPEED_ID "
			+ ") countTable "
			+ ") rankTable "
			//+ "where rankTable.RNK = 1 and rownum <= :NUMBER_OF_ROWS";
			+ "where rankTable.indexWithinGroup <= (:NUMBER_OF_ROWS)/2 and rownum <= :NUMBER_OF_ROWS";
	
	String sqlGetSalesHistoryPercentageRecordsByAccessType_OLD = "select ROUND(count(*)*100/(select count(*) from SALES_TRANSACTION_HISTORY), 2) as PERCENTAGE, "
			+ "ACCESS_TYPE_ID from SALES_TRANSACTION_HISTORY "
			+ "where ACCESS_TYPE_ID in ('Private Line', 'ETHERNET') "
			+ "group by ACCESS_TYPE_ID order by ACCESS_TYPE_ID";
	
	String sqlGetSalesHistoryPercentageRecordsByAccessType = "select ROUND(firstTable.total_By_Access_Type * 100/secondTable.bcount, 2) PERCENTAGE, firstTable.ACCESS_TYPE_ID || '_' || firstTable.ACCESS_SPEED_ID  as ACCESS_TYPE_ID"
			//+ ",(select distinct ACCESS_SPEED_S from SALES_TRANSACTION_HISTORY where ACCESS_TYPE_ID=firstTable.ACCESS_TYPE_ID and ACCESS_SPEED_ID=firstTable.ACCESS_SPEED_ID) ACCESS_SPEED_S "
			+ ",(select distinct ACCESS_SPEED_S from SALES_TRANS_HISTORY_MIS_EXP where ACCESS_TYPE_ID=firstTable.ACCESS_TYPE_ID and ACCESS_SPEED_ID=firstTable.ACCESS_SPEED_ID) ACCESS_SPEED_S "
			+ "from ( select sum(mycount) total_By_Access_Type, ACCESS_TYPE_ID, ACCESS_SPEED_ID  from ( "
			//+ " select ACCESS_TYPE_ID, ACCESS_SPEED_ID, count(*) mycount from SALES_TRANSACTION_HISTORY  group by ACCESS_TYPE_ID, ACCESS_SPEED_ID )  "
			+ " select ACCESS_TYPE_ID, ACCESS_SPEED_ID, count(*) mycount from SALES_TRANS_HISTORY_MIS_EXP  group by ACCESS_TYPE_ID, ACCESS_SPEED_ID )  "
			+ "a group by a.ACCESS_TYPE_ID, a.ACCESS_SPEED_ID ) firstTable, "
			//+ "(select count(*) bcount from SALES_TRANSACTION_HISTORY) secondTable";
			+ "(select count(*) bcount from SALES_TRANS_HISTORY_MIS_EXP) secondTable "
			+ "order by PERCENTAGE desc";
	
	String sqlGetSalesRulesForMISEXPByAccessTypeAndAccessSpeed = "select * from sales_rules_mis_exp where  access_type = :ACCESS_TYPE_ID and"
			  + "  ACCESS_SPEED_ID = :ACCESS_SPEED_ID and MRC is not null and ROWNUM <= :NUMBER_OF_ROWS and PORT_SPEED_ID <= :ACCESS_SPEED_ID";
	
	String sqlGetSalesRulesForMISEXPByAccessTypeAndAccessSpeedAndPortSpeed = "select * from sales_rules_mis_exp where  access_type = :ACCESS_TYPE_ID and"
			  + "  ACCESS_SPEED_ID = :ACCESS_SPEED_ID and PORT_SPEED_ID =:PORT_SPEED_ID  and MRC is not null and ROWNUM <= 1";
	
    String sqlFindRecommendedVnfDevices = "With tInline as ( " + 
            "select * from SALES_VNF_RULES where ROWID in ( " + 
            "  select min(ROWID) " + 
            "       from SALES_VNF_RULES a where a.RATE <> 0 " + 
            "       group by a.MANAGEMENT_TYPE, SUBSTR(a.VNF_ID, 0, INSTR(a.VNF_ID, '-', 1)-1), a.RATE " + 
            "       )  " + 
            "     )  " + 
            "     select RULE_ID,VNF_ID,VIRTUAL_FEATURE_NAME,TYPE_OF_RATE,CURRENCY,RATE,EXTERNAL_RATE_ID,ACTIVE_YN,MANAGEMENT_TYPE  from ( " + 
            "       select  " + 
            "         dense_rank() over (partition by a.MANAGEMENT_TYPE, SUBSTR(a.VNF_ID, 0, INSTR(a.VNF_ID, '-', 1)-1) order by a.RATE desc) RNK, " + 
            "         a.*  " + 
            "       from tInline a where a.RATE <> 0 and a.ACTIVE_YN='Y'" +  
            "     ) t1  " + 
            "     where t1.RNK <= 1 " + 
            "     order by VNF_ID ";
    
    String sqlFindRecommendedUcpeDevices = "select RULE_ID, DEVICE_ID, MANUFACTURE_NAME, MODEL_NAME, STORAGE, CURRENCY, MRC_RATE, NRC_RATE, EXTERNAL_RATE_ID, ACTIVE_YN  from ("
    		+ "select "
    		+ "dense_rank() over (partition by SUBSTR(a.MODEL_NAME, 0, decode(INSTR(a.MODEL_NAME, '-', 1)-1, -1, length(a.MODEL_NAME), INSTR(a.MODEL_NAME, '-', 1)-1)) order by a.MRC_RATE desc) RNK, "
    		+ "a.* "
    		+ "from SALES_UCPE_RULES a where a.ACTIVE_YN = 'Y' "
    		+ ") t1 "
    		+ "where t1.RNK <=1 "
    		+ " order by RULE_ID";
	
}
