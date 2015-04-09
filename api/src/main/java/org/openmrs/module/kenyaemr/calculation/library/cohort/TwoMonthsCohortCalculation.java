/**
 * The contents of this file are subject to the OpenMRS Public License
 * Version 1.0 (the "License"); you may not use this file except in
 * compliance with the License. You may obtain a copy of the License at
 * http://license.openmrs.org
 *
 * Software distributed under the License is distributed on an "AS IS"
 * basis, WITHOUT WARRANTY OF ANY KIND, either express or implied. See the
 * License for the specific language governing rights and limitations
 * under the License.
 *
 * Copyright (C) OpenMRS, LLC.  All Rights Reserved.
 */
package org.openmrs.module.kenyaemr.calculation.library.cohort;

import org.openmrs.Obs;
import org.openmrs.api.ObsService;
import org.openmrs.api.PersonService;
import org.openmrs.api.context.Context;
import org.openmrs.calculation.patient.PatientCalculationContext;
import org.openmrs.calculation.result.CalculationResultMap;
import org.openmrs.calculation.result.SimpleResult;
import org.openmrs.module.kenyacore.calculation.AbstractPatientCalculation;
import org.openmrs.module.kenyaemr.Dictionary;

import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * Calculates cohort of a patient using difference between encounter and next visit dates
 *
 */
public class TwoMonthsCohortCalculation extends AbstractPatientCalculation {

	@Override
	public CalculationResultMap evaluate(Collection<Integer> cohort, Map<String, Object> parameterValues, PatientCalculationContext context) {

		CalculationResultMap resultMap = calculate(new PatientCohortCalculation(), cohort, context);
		CalculationResultMap ret = new CalculationResultMap();

		for (Integer ptid : cohort) {
			PatientCohortCategoryInfo info = (PatientCohortCategoryInfo)resultMap.get(ptid).getValue();
			if (info != null) {
				if (2 == info.getCohort() && "Month".equals(info.getUnit())) {
					ret.put(ptid, new SimpleResult(info, this));
				}
			}
		}

		return ret;
	}

}
