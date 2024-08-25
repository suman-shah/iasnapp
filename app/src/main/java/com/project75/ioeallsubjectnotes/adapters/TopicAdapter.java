package com.project75.ioeallsubjectnotes.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import androidx.cardview.widget.CardView;

import com.project75.ioeallsubjectnotes.R;
import com.project75.ioeallsubjectnotes.model.BasicElectricalEngineering;
import com.project75.ioeallsubjectnotes.model.EngineeringDrawingI;
import com.project75.ioeallsubjectnotes.model.EngineeringPhysics;
import com.project75.ioeallsubjectnotes.model.Topics;
import com.project75.ioeallsubjectnotes.model.AppliedMathematics;
import com.project75.ioeallsubjectnotes.model.AppliedMechanics;
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
import com.project75.ioeallsubjectnotes.model.EngineeringChemistry;
import com.project75.ioeallsubjectnotes.model.EngineeringDrawingII;
import com.project75.ioeallsubjectnotes.model.EngineeringEconomics;
import com.project75.ioeallsubjectnotes.model.EngineeringMathematicsI;
import com.project75.ioeallsubjectnotes.model.EngineeringMathematicsII;
import com.project75.ioeallsubjectnotes.model.EngineeringMathematicsIII;
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
import com.project75.ioeallsubjectnotes.model.TransmissionandDistributionSystemDesign;
import com.project75.ioeallsubjectnotes.model.UtilizationofElectricalEnergy;
import com.project75.ioeallsubjectnotes.model.WorkshopTechnology;

import java.util.List;

public class TopicAdapter extends BaseAdapter {

    private final List<Topics> topicsList;
    private final Context context;
    private final LayoutInflater layoutInflater;

    public TopicAdapter(Context context, List<Topics> topicsList) {
        this.context = context;
        this.topicsList = topicsList;
        this.layoutInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return topicsList.size();
    }

    @Override
    public Object getItem(int position) {
        return topicsList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = layoutInflater.inflate(R.layout.topics_item, parent, false);
        }

        TextView topicName = convertView.findViewById(R.id.topicTitle);
        topicName.setText(topicsList.get(position).getTopicName());

        CardView cardView = convertView.findViewById(R.id.topicClick);
        cardView.setOnClickListener(v -> {
            String topicNameStr = topicsList.get(position).getTopicName();
            Intent intent = getIntentForTopic(topicNameStr);
            if (intent != null) {
                intent.putExtra("topic_name", topicNameStr);
                context.startActivity(intent);
            }
        });

        return convertView;
    }

    private Intent getIntentForTopic(String topicName) {
        switch (topicName) {
            case "Engineering Drawing I":
                return new Intent(context, EngineeringDrawingI.class);
            case "Engineering Physics":
                return new Intent(context, EngineeringPhysics.class);
            case "Applied Mechanics":
                return new Intent(context, AppliedMechanics.class);
            case "Basic Electrical Engineering":
                return new Intent(context, BasicElectricalEngineering.class);
            case "Engineering Mathematics I":
                return new Intent(context, EngineeringMathematicsI.class);
            case "Computer Programming":
                return new Intent(context, ComputerProgramming.class);
            case "Engineering Mathematics II":
                return new Intent(context, EngineeringMathematicsII.class);
            case "Engineering Drawing II":
                return new Intent(context, EngineeringDrawingII.class);
            case "Engineering Chemistry":
                return new Intent(context, EngineeringChemistry.class);
            case "Fundamental of Thermodynamics & Heat Transfer":
                return new Intent(context, FundamentalofThermodynamicsHeatTransfer.class);
            case "Workshop Technology":
                return new Intent(context, WorkshopTechnology.class);
            case "Engineering Mathematics III":
                return new Intent(context, EngineeringMathematicsIII.class);
            case "Object Oriented Programming":
                return new Intent(context, ObjectOrientedProgramming.class);
            case "Electric Circuit Theory":
                return new Intent(context, ElectricCircuitTheory.class);
            case "Electronics Devices and Circuit":
                return new Intent(context, ElectricCircuitTheory.class);
            case "Digital Logic":
                return new Intent(context, DigitalLogic.class);
            case "Electrical Engineering Material":
                return new Intent(context, ElectricalEngineeringMaterial.class);
            case "Electromagnetic":
                return new Intent(context, Electromagnetic.class);
            case "Numerical Method":
                return new Intent(context, NumericalMethod.class);
            case "Applied Mathematics":
                return new Intent(context, AppliedMathematics.class);
            case "Instrumentation I":
                return new Intent(context, InstrumentationI.class);
            case "Microprocessor":
                return new Intent(context, Microprocessor.class);
            case "Power System Analysis I":
                return new Intent(context, PowerSystemAnalysisI.class);
            case "Electrical Machines I":
                return new Intent(context, ElectricalMachinesI.class);
            case "Electric Machines - II":
                return new Intent(context, ElectricMachinesII.class);
            case "Electric Machine Design":
                return new Intent(context, ElectricMachineDesign.class);
            case "Power System Analysis II":
                return new Intent(context, PowerSystemAnalysisII.class);
            case "Communication English":
                return new Intent(context, CommunicationEnglish.class);
            case "Probability and Statistics":
                return new Intent(context, ProbabilityandStatistics.class);
            case "Control System":
                return new Intent(context, ControlSystem.class);
            case "Instrumentation II":
                return new Intent(context, InstrumentationII.class);
            case "Engineering Economic":
                return new Intent(context, EngineeringEconomics.class);
            case "Hydro Power":
                return new Intent(context, HydroPower.class);
            case "Switchgear & Protection":
                return new Intent(context, SwitchgearANDProtection.class);
            case "Digital Control System":
                return new Intent(context, DigitalControlSystem.class);
            case "Industrial Power Distribution & Illumination":
                return new Intent(context, IndustrialPowerDistributionANDIllumination.class);
            case "Signal Analysis":
                return new Intent(context, SignalAnalysis.class);
            case "Project Engineering":
                return new Intent(context, ProjectEngineering.class);
            case "Technology Environment and Society":
                return new Intent(context, TechnologyEnvironmentandSociety.class);
            case "Power Electronics":
                return new Intent(context, PowerElectronics.class);
            case "Organization and Management":
                return new Intent(context, OrganizationandManagement.class);
            case "Utilization of Electrical Energy":
                return new Intent(context, UtilizationofElectricalEnergy.class);
            case "Power Plant Equipment":
                return new Intent(context, PowerPlantEquipment.class);
            case "Project (Part A)":
                return new Intent(context, ProjectPartA.class);
            case "Elective I : Energy Electrical System Management":
                return new Intent(context, ElectiveIEnergyElectricalSystemManagement.class);
            case "Elective I : Reliability Engineering":
                return new Intent(context, ElectiveIReliabilityEngineering.class);
            case "Elective I : Rural Electrification":
                return new Intent(context, ElectiveIRuralElectrification.class);
            case "Engineering Professional Practice":
                return new Intent(context, EngineeringProfessionalPractice.class);
            case "High Voltage Engineering":
                return new Intent(context, HighVoltageEngineering.class);
            case "Power Plant Design":
                return new Intent(context, PowerPlantDesign.class);
            case "Transmission and Distribution System Design":
                return new Intent(context, TransmissionandDistributionSystemDesign.class);
            case "Project (Part B)":
                return new Intent(context, ProjectPartB.class);
            case "Elective II : Advanced Power System Analysis":
                return new Intent(context, ElectiveIIAdvancedPowerSystemAnalysis.class);
            case "Elective II : Applied Photovoltaic":
                return new Intent(context, ElectiveIIAppliedPhotovoltaic.class);
            case "Elective II : Biomedical Instrumentation":
                return new Intent(context, ElectiveIIBiomedicalInstrumentation.class);
            case "Elective III : Artificial Neural Network":
                return new Intent(context, ElectiveIIIArtificialNeuralNetwork.class);
            case "Elective III : Micro-Hydro Power":
                return new Intent(context, ElectiveIIIMicroHydroPower.class);
            case "Elective III : Wind Energy Conversion System":
                return new Intent(context, ElectiveIIIWindEnergyConversionSystem.class);
            default:
                return null;
        }
    }

}
