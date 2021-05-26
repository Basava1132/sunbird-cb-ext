package org.sunbird.workallocation.util;

import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.sunbird.core.exception.BadRequestException;
import org.sunbird.workallocation.model.SearchCriteria;
import org.sunbird.workallocation.model.WorkAllocationDTO;
import org.sunbird.workallocation.model.WorkAllocationDTOV2;
import org.sunbird.workallocation.model.WorkOrderDTO;

@Component
public class Validator {

	public void validateCriteria(SearchCriteria criteria) {
		if (StringUtils.isEmpty(criteria.getDepartmentName())) {
			throw new BadRequestException("Department name can not be empty!");
		}
		if (StringUtils.isEmpty(criteria.getStatus())) {
			throw new BadRequestException("Status can not be empty!");
		}
		if (!WorkAllocationConstants.STATUS_LIST.contains(criteria.getStatus())) {
			throw new BadRequestException("Trying to send wrong status!");
		}
	}

	public void validateWorkAllocationReq(WorkAllocationDTO workAllocation, String reqType) {
		if (StringUtils.isEmpty(workAllocation.getStatus())) {
			throw new BadRequestException("Status can not be empty!");
		}

		if (WorkAllocationConstants.ADD.equalsIgnoreCase(reqType)
				&& !WorkAllocationConstants.DRAFT_STATUS.equalsIgnoreCase(workAllocation.getStatus())) {
			throw new BadRequestException("Status should be 'draft' in ADD operation");
		}

		if (WorkAllocationConstants.UPDATE.equalsIgnoreCase(reqType) && StringUtils.isEmpty(workAllocation.getWaId())) {
			throw new BadRequestException("Wa Id can not be empty!");
		}
		if (WorkAllocationConstants.UPDATE.equalsIgnoreCase(reqType)
				&& StringUtils.isEmpty(workAllocation.getUserId())) {
			throw new BadRequestException("User Id can not be empty!");
		}

		if (StringUtils.isEmpty(workAllocation.getDeptId())) {
			throw new BadRequestException("Department id can not be empty!");
		}
		if (StringUtils.isEmpty(workAllocation.getDeptName())) {
			throw new BadRequestException("Department name can not be empty!");
		}
	}

	public void validateWorkOrder(WorkOrderDTO workOrderDTO, String reqType) {
		if (StringUtils.isEmpty(workOrderDTO.getDeptId()) || StringUtils.isEmpty(workOrderDTO.getDeptName())) {
			throw new BadRequestException("Department id/name should not be empty!");
		}
		if (StringUtils.isEmpty(workOrderDTO.getName())) {
			throw new BadRequestException("Work order name should not be empty!");
		}
		if (WorkAllocationConstants.UPDATE.equalsIgnoreCase(reqType)) {
			if (StringUtils.isEmpty(workOrderDTO.getId())) {
				throw new BadRequestException("Work order Id should not be empty!");
			}
			if (StringUtils.isEmpty(workOrderDTO.getStatus())) {
				throw new BadRequestException("Work order status should not be empty!");
			}
		}
	}
	public void addWorkAllocation(WorkAllocationDTOV2 workAllocationDTOV2) {
		if(StringUtils.isEmpty(workAllocationDTOV2.getWorkOrderId())){
		throw new BadRequestException("Work order Id should not be empty!");
		}
		if(StringUtils.isEmpty(workAllocationDTOV2.getUserName())){
			throw new BadRequestException("User name should not be empty!");
		}
	}
}
