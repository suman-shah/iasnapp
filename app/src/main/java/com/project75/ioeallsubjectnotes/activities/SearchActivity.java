package com.project75.ioeallsubjectnotes.activities;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.project75.ioeallsubjectnotes.R;
import com.project75.ioeallsubjectnotes.adapters.TopicAdapter;
import com.project75.ioeallsubjectnotes.model.Topics;

import java.util.ArrayList;
import java.util.List;
import android.content.Context;
import android.content.Intent;
import android.database.DataSetObserver;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListAdapter;
import android.widget.TextView;
import android.widget.Toast;

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
public class SearchActivity extends AppCompatActivity {

    private ListView listView;
    private EditText searchEditText;
    private TopicAdapter adapter;
    private List<Topics> allTopicsList;
    private List<Topics> filteredTopicsList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_search);

        listView = findViewById(R.id.list_view);
        searchEditText = findViewById(R.id.search_edit_text);

        allTopicsList = getAllTopics(); // Initialize the list of all topics
        filteredTopicsList = new ArrayList<>(allTopicsList);
        adapter = new TopicAdapter(this, filteredTopicsList);
        listView.setAdapter((ListAdapter) adapter);

        // Retrieve the query from the Intent
        String query = getIntent().getStringExtra("query");
        boolean hasMatch = false;

        // Set the query to the searchEditText
        if (query != null && !query.isEmpty()) {
            searchEditText.setText(query);
        }

        searchEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int start, int before, int count) {}

            @Override
            public void onTextChanged(CharSequence charSequence, int start, int before, int count) {
                filterTopics(charSequence.toString());

            }

            @Override
            public void afterTextChanged(android.text.Editable editable) {}
        });


        listView.setOnItemClickListener((adapterView, view, position, id) -> {
            Topics selectedTopic = filteredTopicsList.get(position);
            Intent intent = new Intent(SearchActivity.this, getActivityForTopic(selectedTopic.getTopicName()));
            intent.putExtra("topic_name", selectedTopic.getTopicName());
            startActivity(intent);
        });
        // Perform the initial filter based on the retrieved query
        if (query != null && !query.isEmpty()) {
            filterTopics(query);
            hasMatch = true;
        }
        // Show toast message if no topics match the query
        if (filteredTopicsList.isEmpty() && !query.isEmpty()) {
            Toast.makeText(this, "No matching subject found 😢", Toast.LENGTH_SHORT).show();
        } else if (hasMatch) {
            // Show toast message if matching topics are found
            Toast.makeText(this, "Subject found 😊", Toast.LENGTH_SHORT).show();
        }

    }


    private void filterTopics(String query) {
        filteredTopicsList.clear();
        boolean hasMatch = false;
        if (TextUtils.isEmpty(query)) {
            filteredTopicsList.addAll(allTopicsList);
        } else {
            for (Topics topic : allTopicsList) {
                if (topic.getTopicName().toLowerCase().contains(query.toLowerCase())) {
                    filteredTopicsList.add(topic);
                    hasMatch = true;
                }
            }
        }// Show toast message if no topics match the query
        if (filteredTopicsList.isEmpty() && !query.isEmpty()) {
            Toast.makeText(this, "Subject not found 🤔", Toast.LENGTH_SHORT).show();
        } else if (hasMatch) {
            // Show toast message if matching topics are found
            Toast.makeText(this, "Subject found 😍", Toast.LENGTH_SHORT).show();
        }
        adapter.notifyDataSetChanged(); // Notify adapter about data changes
    }


    private List<Topics> getAllTopics() {
        // Initialize and return the list of all topics
        List<Topics> topics = new ArrayList<>();
        // Add your topics data here
        topics.add(new Topics("Engineering Drawing I", ""));
        topics.add(new Topics("Engineering Physics", ""));
        topics.add(new Topics("Applied Mechanics", ""));
        topics.add(new Topics("Basic Electrical Engineering", ""));
        topics.add(new Topics("Engineering Mathematics I", ""));
        topics.add(new Topics("Computer Programming", ""));
        topics.add(new Topics("Engineering Mathematics II", ""));
        topics.add(new Topics("Engineering Drawing II", ""));
        topics.add(new Topics("Engineering Chemistry", ""));
        topics.add(new Topics("Fundamental of Thermodynamics & Heat Transfer", ""));
        topics.add(new Topics("Workshop Technology", ""));
        topics.add(new Topics("Engineering Mathematics III", ""));
        topics.add(new Topics("Object Oriented Programming", ""));
        topics.add(new Topics("Electric Circuit Theory", ""));
        topics.add(new Topics("Electronics Devices and Circuit", ""));
        topics.add(new Topics("Digital Logic", ""));
        topics.add(new Topics("Electrical Engineering Material", ""));
        topics.add(new Topics("Electromagnetic", ""));
        topics.add(new Topics("Numerical Method", ""));
        topics.add(new Topics("Applied Mathematics", ""));
        topics.add(new Topics("Instrumentation I", ""));
        topics.add(new Topics("Microprocessor", ""));
        topics.add(new Topics("Power System Analysis I", ""));
        topics.add(new Topics("Electrical Machines I", ""));
        topics.add(new Topics("Electric Machines - II", ""));
        topics.add(new Topics("Electric Machine Design", ""));
        topics.add(new Topics("Power System Analysis II", ""));
        topics.add(new Topics("Communication English", ""));
        topics.add(new Topics("Probability and Statistics", ""));
        topics.add(new Topics("Control System", ""));
        topics.add(new Topics("Instrumentation II", ""));
        topics.add(new Topics("Engineering Economics", ""));
        topics.add(new Topics("Hydro Power", ""));
        topics.add(new Topics("Switchgear & Protection", ""));
        topics.add(new Topics("Digital Control System", ""));
        topics.add(new Topics("Industrial Power Distribution & Illumination", ""));
        topics.add(new Topics("Signal Analysis", ""));
        topics.add(new Topics("Project Engineering", ""));
        topics.add(new Topics("Technology Environment and Society", ""));
        topics.add(new Topics("Power Electronics", ""));
        topics.add(new Topics("Organization and Management", ""));
        topics.add(new Topics("Utilization of Electrical Energy", ""));
        topics.add(new Topics("Power Plant Equipment", ""));
        topics.add(new Topics("Project (Part A)", ""));
        topics.add(new Topics("Elective I : Energy Electrical System Management", ""));
        topics.add(new Topics("Elective I : Reliability Engineering", ""));
        topics.add(new Topics("Elective I : Rural Electrification", ""));
        topics.add(new Topics("Engineering Professional Practice", ""));
        topics.add(new Topics("High Voltage Engineering", ""));
        topics.add(new Topics("Power Plant Design", ""));
        topics.add(new Topics("Transmission and Distribution System Design", ""));
        topics.add(new Topics("Project (Part B)", ""));
        topics.add(new Topics("Elective II : Advanced Power System Analysis", ""));
        topics.add(new Topics("Elective II : Applied Photovoltaic", ""));
        topics.add(new Topics("Elective II : Biomedical Instrumentation", ""));
        topics.add(new Topics("Elective III : Artificial Neural Network", ""));
        topics.add(new Topics("Elective III : Micro-Hydro Power", ""));
        topics.add(new Topics("Elective III : Wind Energy Conversion System", ""));

        // Add more topics
        return topics;
    }

    private Class<?> getActivityForTopic(String topicName) {
        switch (topicName) {
            case "Engineering Drawing I":
                return EngineeringDrawingI.class;
            case "Engineering Physics":
                return EngineeringPhysics.class;
            case "Applied Mechanics":
                return AppliedMechanics.class;
            case "Basic Electrical Engineering":
                return BasicElectronicsEngineering.class;
            case "Engineering Mathematics I":
                return EngineeringMathematicsI.class;
            case "Computer Programming":
                return ComputerProgramming.class;
            case "Engineering Mathematics II":
                return EngineeringMathematicsII.class;
            case "Engineering Drawing II":
                return EngineeringDrawingII.class;
            case "Engineering Chemistry":
                return EngineeringChemistry.class;
            case "Fundamental of Thermodynamics & Heat Transfer":
                return FundamentalofThermodynamicsHeatTransfer.class;
            case "Workshop Technology":
                return WorkshopTechnology.class;
            case "Engineering Mathematics III":
                return EngineeringMathematicsIII.class;
            case "Object Oriented Programming":
                return ObjectOrientedProgramming.class;
            case "Electric Circuit Theory":
                return ElectricCircuitTheory.class;
            case "Electronics Devices and Circuit":
                return ElectronicsDevicesandCircuit.class;
            case "Digital Logic":
                return DigitalLogic.class;
            case "Electrical Engineering Material":
                return ElectricalEngineeringMaterial.class;
            case "Electromagnetic":
                return Electromagnetic.class;
            case "Numerical Method":
                return NumericalMethod.class;
            case "Applied Mathematics":
                return AppliedMathematics.class;
            case "Instrumentation I":
                return InstrumentationI.class;
            case "Microprocessor":
                return Microprocessor.class;
            case "Power System Analysis I":
                return PowerSystemAnalysisI.class;
            case "Electrical Machines I":
                return ElectricalMachinesI.class;
            case "Electric Machines - II":
                return ElectricMachinesII.class;
            case "Electric Machine Design":
                return ElectricMachineDesign.class;
            case "Power System Analysis II":
                return PowerSystemAnalysisII.class;
            case "Communication English":
                return CommunicationEnglish.class;
            case "Probability and Statistics":
                return ProbabilityandStatistics.class;
            case "Control System":
                return ControlSystem.class;
            case "Instrumentation II":
                return InstrumentationII.class;
            case "Engineering Economics":
                return EngineeringEconomics.class;
            case "Hydro Power":
                return HydroPower.class;
            case "Switchgear & Protection":
                return SwitchgearANDProtection.class;
            case "Digital Control System":
                return DigitalControlSystem.class;
            case "Industrial Power Distribution & Illumination":
                return IndustrialPowerDistributionANDIllumination.class;
            case "Signal Analysis":
                return SignalAnalysis.class;
            case "Project Engineering":
                return ProjectEngineering.class;
            case "Technology Environment and Society":
                return TechnologyEnvironmentandSociety.class;
            case "Power Electronics":
                return PowerElectronics.class;
            case "Organization and Management":
                return OrganizationandManagement.class;
            case "Utilization of Electrical Energy":
                return UtilizationofElectricalEnergy.class;
            case "Power Plant Equipment":
                return PowerPlantEquipment.class;
            case "Project (Part A)":
                return ProjectPartA.class;
            case "Elective I : Energy Electrical System Management":
                return ElectiveIEnergyElectricalSystemManagement.class;
            case "Elective I : Reliability Engineering":
                return ElectiveIReliabilityEngineering.class;
            case "Elective I : Rural Electrification":
                return ElectiveIRuralElectrification.class;
            case "Engineering Professional Practice":
                return EngineeringProfessionalPractice.class;
            case "High Voltage Engineering":
                return HighVoltageEngineering.class;
            case "Power Plant Design":
                return PowerPlantDesign.class;
            case "Transmission and Distribution System Design":
                return TransmissionandDistributionSystemDesign.class;
            case "Project (Part B)":
                return ProjectPartB.class;
            case "Elective II : Advanced Power System Analysis":
                return ElectiveIIAdvancedPowerSystemAnalysis.class;
            case "Elective II : Applied Photovoltaic":
                return ElectiveIIAppliedPhotovoltaic.class;
            case "Elective II : Biomedical Instrumentation":
                return ElectiveIIBiomedicalInstrumentation.class;
            case "Elective III : Artificial Neural Network":
                return ElectiveIIIArtificialNeuralNetwork.class;
            case "Elective III : Micro-Hydro Power":
                return ElectiveIIIMicroHydroPower.class;
            case "Elective III : Wind Energy Conversion System":
                return ElectiveIIIWindEnergyConversionSystem.class;
            default:
                return null;
        }
    }

}
