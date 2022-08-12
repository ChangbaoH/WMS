package ${basePkg}.service.impl;

import ${basePkg}.dao.I${className}DAO;
import ${basePkg}.domain.${className};
import ${basePkg}.query.PageResult;
import ${basePkg}.query.QueryObject;
import ${basePkg}.service.I${className}Service;
import lombok.Setter;

import java.util.List;

/*
 *@Auther:HCB
 *@Date:2022/5/29
 *@Description:IntelliJ IDEA
 *Version:1.0
 */
public class ${className}ServiceImpl implements I${className}Service {

    @Setter
    private I${className}DAO ${objectName}DAO;

    @Override
    public void save(${className} r) {
        ${objectName}DAO.save(r);
    }

    @Override
    public void update(${className} r) {
        ${objectName}DAO.update(r);
    }

    @Override
    public void delete(Long id) {
        ${objectName}DAO.delete(id);
    }

    @Override
    public ${className} get(Long id) {
        return ${objectName}DAO.get(id);
    }

    @Override
    public List<${className}> listAll() {
        return ${objectName}DAO.listAll();
    }

    @Override
    public PageResult query(QueryObject qo) {
        return ${objectName}DAO.query(qo);
    }
}
