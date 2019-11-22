/**
 * This file is part of the INTO-CPS toolchain.
 *
 * Copyright (c) 2017-2019, INTO-CPS Association,
 * c/o Professor Peter Gorm Larsen, Department of Engineering
 * Finlandsgade 22, 8200 Aarhus N.
 *
 * All rights reserved.
 *
 * THIS PROGRAM IS PROVIDED UNDER THE TERMS OF GPL VERSION 3 LICENSE OR
 * THIS INTO-CPS ASSOCIATION PUBLIC LICENSE VERSION 1.0.
 * ANY USE, REPRODUCTION OR DISTRIBUTION OF THIS PROGRAM CONSTITUTES
 * RECIPIENT'S ACCEPTANCE OF THE OSMC PUBLIC LICENSE OR THE GPL 
 * VERSION 3, ACCORDING TO RECIPIENTS CHOICE.
 *
 * The INTO-CPS toolchain  and the INTO-CPS Association Public License are
 * obtained from the INTO-CPS Association, either from the above address, from
 * the URLs: http://www.into-cps.org, and in the INTO-CPS toolchain distribution.
 * GNU version 3 is obtained from: http://www.gnu.org/copyleft/gpl.html.
 *
 * This program is distributed WITHOUT ANY WARRANTY; without
 * even the implied warranty of  MERCHANTABILITY or FITNESS FOR
 * A PARTICULAR PURPOSE, EXCEPT AS EXPRESSLY SET FORTH IN THE
 * BY RECIPIENT SELECTED SUBSIDIARY LICENSE CONDITIONS OF
 * THE INTO-CPS ASSOCIATION.
 *
 * See the full INTO-CPS Association Public License conditions for more details.
 */

package fmi2vdm.elements;

import org.xml.sax.Attributes;
import org.xml.sax.Locator;

public class CoSimulation extends FMUType
{
	public CoSimulation(Attributes attributes, Locator locator)
	{
		super(attributes, locator);

		canHandleVariableCommunicationStepSize = boolOf(attributes, "canHandleVariableCommunicationStepSize");
		canInterpolateInputs = boolOf(attributes, "canInterpolateInputs");
		maxOutputDerivativeOrder = uintOf(attributes, "maxOutputDerivativeOrder");
		providesIntermediateVariableAccess = boolOf(attributes, "providesIntermediateVariableAccess");
		canReturnEarlyAfterIntermediateUpdate = boolOf(attributes, "canReturnEarlyAfterIntermediateUpdate");
		providesHybridCoSimulation = boolOf(attributes, "providesHybridCoSimulation");
		providesScheduledExecutionSimulation = boolOf(attributes, "providesScheduledExecutionSimulation");
		canNotUseBasicCoSimulation = boolOf(attributes, "canNotUseBasicCoSimulation");
	}

	private Boolean canHandleVariableCommunicationStepSize;
	private Boolean canInterpolateInputs;
	private Long maxOutputDerivativeOrder;
	private Boolean providesIntermediateVariableAccess;
	private Boolean canReturnEarlyAfterIntermediateUpdate;
	private Boolean providesHybridCoSimulation;
	private Boolean providesScheduledExecutionSimulation;
	private Boolean canNotUseBasicCoSimulation;

	@Override
	public void toVDM(String indent)
	{
		System.out.println(indent + "mk_CoSimulation");
		System.out.println(indent + "(");
		super.toVDM(indent + "\t");
		System.out.println(",");
		
		printRawAttribute(indent + "\t", canHandleVariableCommunicationStepSize, ",\n");
		printRawAttribute(indent + "\t", canInterpolateInputs, ",\n");
		printRawAttribute(indent + "\t", maxOutputDerivativeOrder, ",\n");
		printRawAttribute(indent + "\t", providesIntermediateVariableAccess, ",\n");
		printRawAttribute(indent + "\t", canReturnEarlyAfterIntermediateUpdate, ",\n");
		printRawAttribute(indent + "\t", providesHybridCoSimulation, ",\n");
		printRawAttribute(indent + "\t", providesScheduledExecutionSimulation, ",\n");
		printRawAttribute(indent + "\t", canNotUseBasicCoSimulation, "\n");
		System.out.print(indent + ")");
	}
}
