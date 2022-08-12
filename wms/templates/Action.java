package ${basePkg}.web.action;

import ${basePkg}.domain.${className};
import ${basePkg}.query.${className}QueryObject;
import ${basePkg}.service.I${className}Service;
import ${basePkg}.util.RequiredPermission;
import com.opensymphony.xwork2.interceptor.annotations.InputConfig;
import lombok.Getter;
import lombok.Setter;

public class ${className}Action extends BaseAction {

	private static final long serialVersionUID = 1L;
	
	@Setter
	private I${className}Service ${objectName}Service;
	@Getter
	private ${className} ${objectName} = new ${className}();

	@Getter
	private ${className}QueryObject qo = new ${className}QueryObject();

	@RequiredPermission("${className}列表")
	@InputConfig(methodName = "input")
	@Override
	public String execute() throws Exception {
		try {
			//putContext("${className}s", ${className}Service.query(qo));
			putContext("pageResult", ${objectName}Service.query(qo));
		}catch (Exception e){
			e.printStackTrace();
			addActionError(e.getMessage());
		}

		return LIST;
	}

	@RequiredPermission("${className}编辑")
	//进入录入界面
	public String input() throws Exception {
		if (${objectName}.getId() != null){
			${objectName} = ${objectName}Service.get(${objectName}.getId());
		}
		return INPUT;
	}

	@RequiredPermission("${className}删除")
	//删除操作
	public String delete() throws Exception {
		if (${objectName}.getId() != null){
			${objectName}Service.delete(${objectName}.getId());
		}
		return NONE;
	}

	@RequiredPermission("${className}保存或更新")
	//新增或更新操作
	public String saveOrUpdate() throws Exception {
		try {
			if (${objectName}.getId() == null){
				${objectName}Service.save(${objectName});
				addActionMessage("保存成功");
			}else {
				${objectName}Service.update(${objectName});
				addActionMessage("修改成功");
			}
		}catch (Exception e){
			e.printStackTrace();
			addActionError(e.getMessage());
		}

		return SUCCESS;
	}


	//只会在SaveOrUpdate前执行
	public void prepareSaveOrUpdate() throws Exception {
		if (${objectName}.getId() != null){
			${objectName} = ${objectName}Service.get(${objectName}.getId());
		}
	}

}
