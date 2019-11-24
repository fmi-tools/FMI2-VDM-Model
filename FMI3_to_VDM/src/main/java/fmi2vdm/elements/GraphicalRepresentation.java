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

import org.xml.sax.Locator;

public class GraphicalRepresentation extends Element
{
	private CoordinateSystem coordinateSystem;
	private Icon icon;
	private GraphicalTerminal terminal;

	public GraphicalRepresentation(Locator locator)
	{
		super(locator);
	}

	@Override
	public void add(Element element)
	{
		if (element instanceof CoordinateSystem)
		{
			coordinateSystem = (CoordinateSystem) element;
		}
		else if (element instanceof Icon)
		{
			icon = (Icon) element;
		}
		else if (element instanceof GraphicalTerminal)
		{
			terminal = (GraphicalTerminal) element;
		}
		else
		{
			super.add(element);
		}
	}

	@Override
	public void toVDM(String indent)
	{
		System.out.println(indent + "mk_GraphicalRepresentation");
		System.out.println(indent + "(");
		System.out.println(indent + "\t" + lineNumber + ",  -- Line");

		if (coordinateSystem != null)
		{
			coordinateSystem.toVDM(indent + "\t");
			System.out.println(",");
		}
		else
		{
			System.out.println(indent + "\tnil,");
		}

		if (icon != null)
		{
			icon.toVDM(indent + "\t");
			System.out.println(",");
		}
		else
		{
			System.out.println(indent + "\tnil,");
		}

		if (terminal != null)
		{
			terminal.toVDM(indent + "\t");
			System.out.println("");
		}
		else
		{
			System.out.println(indent + "\tnil");
		}

		System.out.print(indent + ")");
	}

	@Override
	public void validate(String root)
	{
		validate(root, "coordinateSystem", coordinateSystem, false);
		validate(root, "icon", icon, false);
		validate(root, "terminal", terminal, false);
	}
}
