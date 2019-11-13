package com.pengniao.rdpm.component;

import com.pengniao.rdpm.entity.TRight;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @program: rdpm
 * @description: 权限grid结构
 * @author: lj
 * @create: 2019-10-22 09:58
 **/
public class GetRightTree {

    private static StringBuilder returnStr1;//带根节点(TreeGrid形式)

    public static String getRightForGrid(List<TRight> list , TRight node)
    {
        returnStr1 = new StringBuilder();
        recursionFnForGrid(list,node);
        return modifyStr(returnStr1.toString());
    }

    public static void recursionFnForGrid(List<TRight> list , TRight node){
        if(hasChild(list,node)){
            returnStr1.append("{\"rightId\":");
            returnStr1.append(""+node.getRightId()+"");
            returnStr1.append(",\"rightName\":");
            returnStr1.append("\""+node.getRightName()+"\"");
            returnStr1.append(",\"action\":");
            returnStr1.append("\""+node.getAction()+"\"");
            returnStr1.append(",\"parentTrId\":");
            returnStr1.append("\""+node.getParentTrId()+"\"");
            returnStr1.append(",\"children\":[");
            List<TRight> childList = getChildList(list,node);
            Iterator<TRight> it = childList.iterator();
            while(it.hasNext()){
                TRight n = (TRight)it.next();
                recursionFnForGrid(list,n);
            }
            returnStr1.append("]},");
        }else{
            returnStr1.append("{\"rightId\":");
            returnStr1.append(""+node.getRightId()+"");
            returnStr1.append(",\"rightName\":");
            returnStr1.append("\""+node.getRightName()+"\"");
            returnStr1.append(",\"action\":");
            returnStr1.append("\""+node.getAction()+"\"");
            returnStr1.append(",\"parentTrId\":");
            returnStr1.append("\""+node.getParentTrId()+"\"");
            returnStr1.append("},");
        }


    }

    public static boolean hasChild(List<TRight> list, TRight node){  //判断是否有子节点
        return getChildList(list,node).size()>0?true:false;
    }

    public static List<TRight> getChildList(List<TRight> list , TRight node){  //得到子节点列表
        List<TRight> li = new ArrayList<TRight>();
        Iterator<TRight> it = list.iterator();
        while(it.hasNext()){
            TRight n = (TRight)it.next();
            if(n.getParentTrId() != null)
            {
                if(n.getParentTrId().equals(node.getRightId())){
                    li.add(n);
                }
            }
        }
        return li;
    }

    public static String modifyStr(String returnStr){
        return ("["+returnStr+"]").replaceAll(",]", "]");

    }
}
