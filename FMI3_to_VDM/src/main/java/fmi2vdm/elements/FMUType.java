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

public class FMUType extends Element
{
	private ElementList<VendorAnnotations> annotations;
	private String modelIdentifier;
	private Boolean needsExecutionTool;
	private Boolean canBeInstantiatedOnlyOncePerProcess;
	private Boolean canNotUseMemoryManagementFunctions;
	private Boolean canGetAndSetFMUState;
	private Boolean canSerializeFMUState;
	private Boolean providesDirectionalDerivative;
	private Boolean providesPerElementDependencies;;

	protected FMUType(Attributes attributes, Locator locator)
	{
		super(locator);
		
		modelIdentifier = stringOf(attributes, "modelIdentifier");
		needsExecutionTool = boolOf(attributes, "needsExecutionTool");
		canBeInstantiatedOnlyOncePerProcess = boolOf(attributes, "canBeInstantiatedOnlyOncePerProcess");
		canNotUseMemoryManagementFunctions = boolOf(attributes, "canNotUseMemoryManagementFunctions");
		canGetAndSetFMUState = boolOf(attributes, "canGetAndSetFMUState");
		canSerializeFMUState = boolOf(attributes, "canSerializeFMUState");
		providesDirectionalDerivative = boolOf(attributes, "providesDirectionalDerivative");
		providesPerElementDependencies = boolOf(attributes, "providesPerElementDependencies");
	}
	
	@Override
	public void add(Element element)
	{
		if (element instanceof VendorAnnotations)
		{
			if (annotations == null)
			{
				annotations = new ElementList<VendorAnnotations>();
			}
			
			annotations.add(element);
		}
		else
		{
			super.add(element);
		}
	}

	@Override
	public void toVDM(String indent)
	{
		System.out.println(indent + "mk_fmi3FMUType");
		System.out.println(indent + "(");
		System.out.println(indent + "\t" + lineNumber + ",  -- Line");
		
		printSequence(indent + "\t", annotations, ",\n");
		
		printStringAttribute(indent + "\t", modelIdentifier, ",\n");
		printRawAttribute(indent + "\t", needsExecutionTool, ",\n");
		printRawAttribute(indent + "\t", canBeInstantiatedOnlyOncePerProcess, ",\n");
		printRawAttribute(indent + "\t", canNotUseMemoryManagementFunctions, ",\n");
		printRawAttribute(indent + "\t", canGetAndSetFMUState, ",\n");
		printRawAttribute(indent + "\t", canSerializeFMUState, ",\n");
		printRawAttribute(indent + "\t", providesDirectionalDerivative, ",\n");
		printRawAttribute(indent + "\t", providesPerElementDependencies, "\n");
		System.out.print(indent + ")");
	}

	@Override
	public void validate(String root)
	{
		validate(root, "modelIdentifier", modelIdentifier, true);
		validate(root, "annotations", annotations, false);
	}
}