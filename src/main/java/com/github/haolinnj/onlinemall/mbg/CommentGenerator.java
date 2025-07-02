package com.github.haolinnj.onlinemall.mbg;

import ch.qos.logback.core.util.StringUtil;
import org.mybatis.generator.api.IntrospectedColumn;
import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.dom.java.Field;
import org.mybatis.generator.internal.DefaultCommentGenerator;
import org.mybatis.generator.internal.util.StringUtility;

import java.util.Properties;

public class CommentGenerator extends DefaultCommentGenerator {
    private boolean addRemarkComments = false;

    /**
     * Read All properties from the configuration
     */
    @Override
    public void addConfigurationProperties(Properties props) {
        super.addConfigurationProperties(props);
        this.addRemarkComments = StringUtility.isTrue(props.getProperty("addRemarkComments"));
    }

    @Override
    public void addFieldComment(Field field,
                                IntrospectedTable introspectedTable,
                                IntrospectedColumn introspectedColumn) {
        String remarks = introspectedColumn.getRemarks();

        if(addRemarkComments && StringUtility.stringHasValue(remarks)){
            addFieldJavaDoc(field, remarks);
        }
    }

    /**
     *给model的字段添加注释
     */
    private void addFieldJavaDoc(Field field, String remarks) {
        //文档注释开始
        field.addJavaDocLine("/**");
        //获取数据库字段的备注信息
        String[] remarkLines = remarks.split(System.getProperty("line.separator"));
        for (String remarkLine : remarkLines) {
            field.addJavaDocLine(" * " + remarkLine);
        }
        //addJavadocTag(field, false);
        field.addJavaDocLine(" */");
    }
}
