package com.project75.ioeallsubjectnotes.adapters;

import android.content.Context;
import android.content.Intent;
import android.database.DataSetObserver;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListAdapter;
import android.widget.TextView;

import androidx.cardview.widget.CardView;

import com.project75.ioeallsubjectnotes.R;
import com.project75.ioeallsubjectnotes.model.AppliedMathematics;
import com.project75.ioeallsubjectnotes.model.AppliedMechanics;
import com.project75.ioeallsubjectnotes.model.BasicElectronicsEngineering;
import com.project75.ioeallsubjectnotes.model.Chapter;
import com.project75.ioeallsubjectnotes.model.CommunicationEnglish;
import com.project75.ioeallsubjectnotes.model.ComputerProgramming;
import com.project75.ioeallsubjectnotes.model.ControlSystem;
import com.project75.ioeallsubjectnotes.model.DigitalControlSystem;
import com.project75.ioeallsubjectnotes.model.DigitalLogic;
import com.project75.ioeallsubjectnotes.model.ElectiveIEnergyElectricalSystemManagement;
import com.project75.ioeallsubjectnotes.model.ElectiveIIAdvancedPowerSystemAnalysis;
import com.project75.ioeallsubjectnotes.model.ElectiveIIAppliedPhotovoltaic;
import com.project75.ioeallsubjectnotes.model.ElectiveIIBiomedicalInstrumentation;
import com.project75.ioeallsubjectnotes.model.ElectiveIIIArtificialNeuralNetwork;
import com.project75.ioeallsubjectnotes.model.ElectiveIIIMicroHydroPower;
import com.project75.ioeallsubjectnotes.model.ElectiveIIIWindEnergyConversionSystem;
import com.project75.ioeallsubjectnotes.model.ElectiveIReliabilityEngineering;
import com.project75.ioeallsubjectnotes.model.ElectiveIRuralElectrification;
import com.project75.ioeallsubjectnotes.model.ElectricCircuitTheory;
import com.project75.ioeallsubjectnotes.model.ElectricMachineDesign;
import com.project75.ioeallsubjectnotes.model.ElectricMachinesII;
import com.project75.ioeallsubjectnotes.model.ElectricalEngineeringMaterial;
import com.project75.ioeallsubjectnotes.model.ElectricalMachinesI;
import com.project75.ioeallsubjectnotes.model.Electromagnetic;
import com.project75.ioeallsubjectnotes.model.ElectronicsDevicesandCircuit;
import com.project75.ioeallsubjectnotes.model.EngineeringChemistry;
import com.project75.ioeallsubjectnotes.model.EngineeringDrawingI;
import com.project75.ioeallsubjectnotes.model.EngineeringDrawingII;
import com.project75.ioeallsubjectnotes.model.EngineeringEconomics;
import com.project75.ioeallsubjectnotes.model.EngineeringMathematicsI;
import com.project75.ioeallsubjectnotes.model.EngineeringMathematicsII;
import com.project75.ioeallsubjectnotes.model.EngineeringMathematicsIII;
import com.project75.ioeallsubjectnotes.model.EngineeringPhysics;
import com.project75.ioeallsubjectnotes.model.EngineeringProfessionalPractice;
import com.project75.ioeallsubjectnotes.model.FundamentalofThermodynamicsHeatTransfer;
import com.project75.ioeallsubjectnotes.model.HighVoltageEngineering;
import com.project75.ioeallsubjectnotes.model.HydroPower;
import com.project75.ioeallsubjectnotes.model.IndustrialPowerDistributionANDIllumination;
import com.project75.ioeallsubjectnotes.model.InstrumentationI;
import com.project75.ioeallsubjectnotes.model.InstrumentationII;
import com.project75.ioeallsubjectnotes.model.Microprocessor;
import com.project75.ioeallsubjectnotes.model.NumericalMethod;
import com.project75.ioeallsubjectnotes.model.ObjectOrientedProgramming;
import com.project75.ioeallsubjectnotes.model.OrganizationandManagement;
import com.project75.ioeallsubjectnotes.model.PowerElectronics;
import com.project75.ioeallsubjectnotes.model.PowerPlantDesign;
import com.project75.ioeallsubjectnotes.model.PowerPlantEquipment;
import com.project75.ioeallsubjectnotes.model.PowerSystemAnalysisI;
import com.project75.ioeallsubjectnotes.model.PowerSystemAnalysisII;
import com.project75.ioeallsubjectnotes.model.ProbabilityandStatistics;
import com.project75.ioeallsubjectnotes.model.ProjectEngineering;
import com.project75.ioeallsubjectnotes.model.ProjectPartA;
import com.project75.ioeallsubjectnotes.model.ProjectPartB;
import com.project75.ioeallsubjectnotes.model.SignalAnalysis;
import com.project75.ioeallsubjectnotes.model.SwitchgearANDProtection;
import com.project75.ioeallsubjectnotes.model.TechnologyEnvironmentandSociety;
import com.project75.ioeallsubjectnotes.model.Topics;
import com.project75.ioeallsubjectnotes.model.TransmissionandDistributionSystemDesign;
import com.project75.ioeallsubjectnotes.model.UtilizationofElectricalEnergy;
import com.project75.ioeallsubjectnotes.model.WorkshopTechnology;

import java.util.List;

public class CustomAdapter implements ExpandableListAdapter {

    List<Chapter> chapterList;
    List<Topics> topicsList;
    Context context;
    LayoutInflater layoutInflater;

    public CustomAdapter(List<Chapter> chapterList, Context context) {
        this.chapterList = chapterList;
        this.context = context;
        this.layoutInflater = LayoutInflater.from(context);
    }


    @Override
    public void registerDataSetObserver(DataSetObserver observer) {

    }

    @Override
    public void unregisterDataSetObserver(DataSetObserver observer) {

    }

    @Override
    public int getGroupCount() {
        return chapterList.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return chapterList.get(groupPosition).getTopicsList().size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return chapterList.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return chapterList.get(groupPosition).getTopicsList().get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {

        convertView = LayoutInflater.from(context).inflate(R.layout.chapter_item, parent, false);

        TextView chapterName = (TextView) convertView.findViewById(R.id.chapterTitle);
        chapterName.setText(chapterList.get(groupPosition).getChapterName());

        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        convertView = LayoutInflater.from(context).inflate(R.layout.topics_item, parent, false);

        TextView topicName = (TextView) convertView.findViewById(R.id.topicTitle);
        topicName.setText(chapterList.get(groupPosition).getTopicsList().get(childPosition).getTopicName());

        CardView cardView = (CardView) convertView.findViewById(R.id.topicClick);
        cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = null;
                Topics topic = chapterList.get(groupPosition).getTopicsList().get(childPosition);
                if (topic.getTopicName().equals("Engineering Drawing I")) {
                    intent = new Intent(context, EngineeringDrawingI.class);
                    intent.putExtra("topic_name", topic.getTopicName());
                }
                if (topic.getTopicName().equals("Engineering Physics")) {
                    intent = new Intent(context, EngineeringPhysics.class);
                    intent.putExtra("topic_name", topic.getTopicName());
                }
                if (topic.getTopicName().equals("Applied Mechanics")) {
                    intent = new Intent(context, AppliedMechanics.class);
                    intent.putExtra("topic_name", topic.getTopicName());
                }
                if (topic.getTopicName().equals("Basic Electrical Engineering")) {
                    intent = new Intent(context, BasicElectronicsEngineering.class);
                    intent.putExtra("topic_name", topic.getTopicName());
                }
                if (topic.getTopicName().equals("Engineering Mathematics I")) {
                    intent = new Intent(context, EngineeringMathematicsI.class);
                    intent.putExtra("topic_name", topic.getTopicName());
                }
                if (topic.getTopicName().equals("Computer Programming")) {
                    intent = new Intent(context, ComputerProgramming.class);
                    intent.putExtra("topic_name", topic.getTopicName());
                }
                if (topic.getTopicName().equals("Engineering Mathematics II")) {
                    intent = new Intent(context, EngineeringMathematicsII.class);
                    intent.putExtra("topic_name", topic.getTopicName());
                }
                if (topic.getTopicName().equals("Engineering Drawing II")) {
                    intent = new Intent(context, EngineeringDrawingII.class);
                    intent.putExtra("topic_name", topic.getTopicName());
                }
                if (topic.getTopicName().equals("Basic Electronics Engineering")) {
                    intent = new Intent(context, BasicElectronicsEngineering.class);
                    intent.putExtra("topic_name", topic.getTopicName());
                }
                if (topic.getTopicName().equals("Engineering Chemistry")) {
                    intent = new Intent(context, EngineeringChemistry.class);
                    intent.putExtra("topic_name", topic.getTopicName());
                }
                if (topic.getTopicName().equals("Fundamental of Thermodynamics & Heat Transfer")) {
                    intent = new Intent(context, FundamentalofThermodynamicsHeatTransfer.class);
                    intent.putExtra("topic_name", topic.getTopicName());
                }
                if (topic.getTopicName().equals("Workshop Technology")) {
                    intent = new Intent(context, WorkshopTechnology.class);
                    intent.putExtra("topic_name", topic.getTopicName());
                }
                if (topic.getTopicName().equals("Engineering Mathematics III")) {
                    intent = new Intent(context, EngineeringMathematicsIII.class);
                    intent.putExtra("topic_name", topic.getTopicName());
                }
                if (topic.getTopicName().equals("Object Oriented Programming")) {
                    intent = new Intent(context, ObjectOrientedProgramming.class);
                    intent.putExtra("topic_name", topic.getTopicName());
                }
                if (topic.getTopicName().equals("Electric Circuit Theory")) {
                    intent = new Intent(context, ElectricCircuitTheory.class);
                    intent.putExtra("topic_name", topic.getTopicName());
                }
                if (topic.getTopicName().equals("Electronics Devices and Circuit")) {
                    intent = new Intent(context, ElectronicsDevicesandCircuit.class);
                    intent.putExtra("topic_name", topic.getTopicName());
                }
                if (topic.getTopicName().equals("Digital Logic")) {
                    intent = new Intent(context, DigitalLogic.class);
                    intent.putExtra("topic_name", topic.getTopicName());
                }
                if (topic.getTopicName().equals("Electrical Engineering Material")) {
                    intent = new Intent(context, ElectricalEngineeringMaterial.class);
                    intent.putExtra("topic_name", topic.getTopicName());
                }
                if (topic.getTopicName().equals("Electromagnetic")) {
                    intent = new Intent(context, Electromagnetic.class);
                    intent.putExtra("topic_name", topic.getTopicName());
                }
                if (topic.getTopicName().equals("Numerical Method")) {
                    intent = new Intent(context, NumericalMethod.class);
                    intent.putExtra("topic_name", topic.getTopicName());
                }
                if (topic.getTopicName().equals("Applied Mathematics")) {
                    intent = new Intent(context, AppliedMathematics.class);
                    intent.putExtra("topic_name", topic.getTopicName());
                }
                if (topic.getTopicName().equals("Instrumentation I")) {
                    intent = new Intent(context, InstrumentationI.class);
                    intent.putExtra("topic_name", topic.getTopicName());
                }
                if (topic.getTopicName().equals("Microprocessor")) {
                    intent = new Intent(context, Microprocessor.class);
                    intent.putExtra("topic_name", topic.getTopicName());
                }
                if (topic.getTopicName().equals("Power System Analysis I")) {
                    intent = new Intent(context, PowerSystemAnalysisI.class);
                    intent.putExtra("topic_name", topic.getTopicName());
                }
                if (topic.getTopicName().equals("Electrical Machines I")) {
                    intent = new Intent(context, ElectricalMachinesI.class);
                    intent.putExtra("topic_name", topic.getTopicName());
                }
                if (topic.getTopicName().equals("Electric Machines - II")) {
                    intent = new Intent(context, ElectricMachinesII.class);
                    intent.putExtra("topic_name", topic.getTopicName());
                }
                if (topic.getTopicName().equals("Electric Machine Design")) {
                    intent = new Intent(context, ElectricMachineDesign.class);
                    intent.putExtra("topic_name", topic.getTopicName());
                }
                if (topic.getTopicName().equals("Power System Analysis II")) {
                    intent = new Intent(context, PowerSystemAnalysisII.class);
                    intent.putExtra("topic_name", topic.getTopicName());
                }
                if (topic.getTopicName().equals("Communication English")) {
                    intent = new Intent(context, CommunicationEnglish.class);
                    intent.putExtra("topic_name", topic.getTopicName());
                }
                if (topic.getTopicName().equals("Probability and Statistics")) {
                    intent = new Intent(context, ProbabilityandStatistics.class);
                    intent.putExtra("topic_name", topic.getTopicName());
                }
                if (topic.getTopicName().equals("Control System")) {
                    intent = new Intent(context, ControlSystem.class);
                    intent.putExtra("topic_name", topic.getTopicName());
                }
                if (topic.getTopicName().equals("Instrumentation II")) {
                    intent = new Intent(context, InstrumentationII.class);
                    intent.putExtra("topic_name", topic.getTopicName());
                }
                if (topic.getTopicName().equals("Engineering Economic")) {
                    intent = new Intent(context, EngineeringEconomics.class);
                    intent.putExtra("topic_name", topic.getTopicName());
                }
                if (topic.getTopicName().equals("Hydro Power")) {
                    intent = new Intent(context, HydroPower.class);
                    intent.putExtra("topic_name", topic.getTopicName());
                }
                if (topic.getTopicName().equals("Switchgear & Protection")) {
                    intent = new Intent(context, SwitchgearANDProtection.class);
                    intent.putExtra("topic_name", topic.getTopicName());
                }
                if (topic.getTopicName().equals("Digital Control System")) {
                    intent = new Intent(context, DigitalControlSystem.class);
                    intent.putExtra("topic_name", topic.getTopicName());
                }
                if (topic.getTopicName().equals("Industrial Power Distribution & Illumination")) {
                    intent = new Intent(context, IndustrialPowerDistributionANDIllumination.class);
                    intent.putExtra("topic_name", topic.getTopicName());
                }
                if (topic.getTopicName().equals("Signal Analysis")) {
                    intent = new Intent(context, SignalAnalysis.class);
                    intent.putExtra("topic_name", topic.getTopicName());
                }
                if (topic.getTopicName().equals("Project Engineering")) {
                    intent = new Intent(context, ProjectEngineering.class);
                    intent.putExtra("topic_name", topic.getTopicName());
                }
                if (topic.getTopicName().equals("Technology Environment and Society")) {
                    intent = new Intent(context, TechnologyEnvironmentandSociety.class);
                    intent.putExtra("topic_name", topic.getTopicName());
                }
                if (topic.getTopicName().equals("Power Electronics")) {
                    intent = new Intent(context, PowerElectronics.class);
                    intent.putExtra("topic_name", topic.getTopicName());
                }
                if (topic.getTopicName().equals("Organization and Management")) {
                    intent = new Intent(context, OrganizationandManagement.class);
                    intent.putExtra("topic_name", topic.getTopicName());
                }
                if (topic.getTopicName().equals("Utilization of Electrical Energy")) {
                    intent = new Intent(context, UtilizationofElectricalEnergy.class);
                    intent.putExtra("topic_name", topic.getTopicName());
                }
                if (topic.getTopicName().equals("Power Plant Equipment")) {
                    intent = new Intent(context, PowerPlantEquipment.class);
                    intent.putExtra("topic_name", topic.getTopicName());
                }
                if (topic.getTopicName().equals("Project (Part A)")) {
                    intent = new Intent(context, ProjectPartA.class);
                    intent.putExtra("topic_name", topic.getTopicName());
                }
                if (topic.getTopicName().equals("Elective I : Energy Electrical System Management")) {
                    intent = new Intent(context, ElectiveIEnergyElectricalSystemManagement.class);
                    intent.putExtra("topic_name", topic.getTopicName());
                }
                if (topic.getTopicName().equals("Elective I : Reliability Engineering")) {
                    intent = new Intent(context, ElectiveIReliabilityEngineering.class);
                    intent.putExtra("topic_name", topic.getTopicName());
                }
                if (topic.getTopicName().equals("Elective I : Rural Electrification")) {
                    intent = new Intent(context, ElectiveIRuralElectrification.class);
                    intent.putExtra("topic_name", topic.getTopicName());
                }
                if (topic.getTopicName().equals("Engineering Professional Practice")) {
                    intent = new Intent(context, EngineeringProfessionalPractice.class);
                    intent.putExtra("topic_name", topic.getTopicName());
                }
                if (topic.getTopicName().equals("High Voltage Engineering")) {
                    intent = new Intent(context, HighVoltageEngineering.class);
                    intent.putExtra("topic_name", topic.getTopicName());
                }
                if (topic.getTopicName().equals("Power Plant Design")) {
                    intent = new Intent(context, PowerPlantDesign.class);
                    intent.putExtra("topic_name", topic.getTopicName());
                }
                if (topic.getTopicName().equals("Transmission and Distribution System Design")) {
                    intent = new Intent(context, TransmissionandDistributionSystemDesign.class);
                    intent.putExtra("topic_name", topic.getTopicName());
                }
                if (topic.getTopicName().equals("Project (Part B)")) {
                    intent = new Intent(context, ProjectPartB.class);
                    intent.putExtra("topic_name", topic.getTopicName());
                }
                if (topic.getTopicName().equals("Elective II : Advanced Power System Analysis")) {
                    intent = new Intent(context, ElectiveIIAdvancedPowerSystemAnalysis.class);
                    intent.putExtra("topic_name", topic.getTopicName());
                }
                if (topic.getTopicName().equals("Elective II : Applied Photovoltaic")) {
                    intent = new Intent(context, ElectiveIIAppliedPhotovoltaic.class);
                    intent.putExtra("topic_name", topic.getTopicName());
                }
                if (topic.getTopicName().equals("Elective II : Biomedical Instrumentation")) {
                    intent = new Intent(context, ElectiveIIBiomedicalInstrumentation.class);
                    intent.putExtra("topic_name", topic.getTopicName());
                }
                if (topic.getTopicName().equals("Elective III : Artificial Neural Network")) {
                    intent = new Intent(context, ElectiveIIIArtificialNeuralNetwork.class);
                    intent.putExtra("topic_name", topic.getTopicName());
                }
                if (topic.getTopicName().equals("Elective III : Micro-Hydro Power")) {
                    intent = new Intent(context, ElectiveIIIMicroHydroPower.class);
                    intent.putExtra("topic_name", topic.getTopicName());
                }
                if (topic.getTopicName().equals("Elective III : Wind Energy Conversion System")) {
                    intent = new Intent(context, ElectiveIIIWindEnergyConversionSystem.class);
                    intent.putExtra("topic_name", topic.getTopicName());
                }








                // Add more else if blocks for other topics and activities

                if (intent != null) {
                    context.startActivity(intent);
                }
            }
        });


        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }

    @Override
    public boolean areAllItemsEnabled() {
        return false;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public void onGroupExpanded(int groupPosition) {

    }

    @Override
    public void onGroupCollapsed(int groupPosition) {

    }

    @Override
    public long getCombinedChildId(long groupId, long childId) {
        return 0;
    }

    @Override
    public long getCombinedGroupId(long groupId) {
        return 0;
    }
}
