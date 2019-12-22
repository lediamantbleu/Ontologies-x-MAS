package Ontologist;

import org.semanticweb.owlapi.model.*;
import org.semanticweb.owlapi.reasoner.OWLReasoner;

import java.io.File;
import java.util.ArrayList;
import java.util.Set;

public class OntPusher {

	private File ontFile;
	private OWLDataFactory dataFactory;
	private OWLReasoner reasoner;
	private String ontNameSpace;
	private OWLOntology ontology;
	private OWLOntologyManager manager;

	public OntPusher(Object[] args) {
		// TODO Auto-generated constructor stub
		this.ontFile = (File) args[0];
		this.ontology = (OWLOntology) args[1];
		this.dataFactory = (OWLDataFactory) args[2];
		this.reasoner = (OWLReasoner) args[3];
		this.ontNameSpace = (String) args[4];
		this.manager = (OWLOntologyManager) args[5];
	}

	public void writeLinkIndividual_WithWeight(String nameOfLink, double weight) {

		boolean alreadyHave=false;

		OWLClass link_class = dataFactory.getOWLClass(IRI.create(ontNameSpace + "Link"));

		Set<OWLNamedIndividual> LinkIndividuals = GetDataFromOntology.getIndividualsOfClass(link_class, reasoner);

		ArrayList<OWLNamedIndividual> LIndividuals= new ArrayList<OWLNamedIndividual> ();
		LIndividuals.addAll(LinkIndividuals);

		for (int i=0; i<LIndividuals.size(); i++) {
			if (LIndividuals.get(i).getIRI().getShortForm().equals(nameOfLink)) {
				alreadyHave=true;
			}
		}

		if (alreadyHave == false) {
			OWLNamedIndividual link_individual = dataFactory.getOWLNamedIndividual(IRI.create(ontNameSpace + nameOfLink));//tut sozdau vozd individa 
			OWLAxiom linkClassDeclaration = dataFactory.getOWLClassAssertionAxiom(link_class, link_individual); // tut privyazka k classu
			AddAxiom addLinkAxiom = new AddAxiom(ontology, linkClassDeclaration); // dlya togo chtob zasunut' v ontologiu
			manager.applyChange(addLinkAxiom);
			
			OWLDataProperty weight_of_link= dataFactory.getOWLDataProperty(IRI.create(ontNameSpace+"LinkWeight"));
			OWLDataPropertyAssertionAxiom weight_property_assertion = dataFactory.getOWLDataPropertyAssertionAxiom(weight_of_link, link_individual,  weight);
			AddAxiom addaxiom1= new AddAxiom(ontology, weight_property_assertion);
			manager.applyChange(addaxiom1);
		}
	}

	public void writeProsumerIndividual(String nameOfPosumer) {
		boolean alreadyHave=false;
		OWLClass prosumer_class = dataFactory.getOWLClass(IRI.create(ontNameSpace + "ESS"));

		Set<OWLNamedIndividual> prosumerIndividuals= GetDataFromOntology.getIndividualsOfClass(prosumer_class, reasoner);

		ArrayList<OWLNamedIndividual> proIndividuals= new ArrayList<OWLNamedIndividual> ();
		proIndividuals.addAll(prosumerIndividuals);

		for (int i=0; i<proIndividuals.size(); i++) {
			if (proIndividuals.get(i).getIRI().getShortForm().equals(nameOfPosumer)) {
				alreadyHave=true;
			}
		}

		if (alreadyHave == false) {
			OWLNamedIndividual prosumer_individual = dataFactory.getOWLNamedIndividual(IRI.create(ontNameSpace + nameOfPosumer));//tut sozdau vozd individa 
			OWLAxiom prosumerClassDeclaration = dataFactory.getOWLClassAssertionAxiom(prosumer_class, prosumer_individual); // tut privyazka k classu
			AddAxiom addProsumerAxiom = new AddAxiom(ontology, prosumerClassDeclaration); // dlya togo chtob zasunut' v ontologiu
			manager.applyChange(addProsumerAxiom); // the same
		}
	}
	
	public void writeObjectProperties(String prosumerName1, String prosumerName2, String nameOfLink) {
		OWLObjectProperty hasLink = dataFactory.getOWLObjectProperty(IRI.create(ontNameSpace+"hasLink"));
		OWLObjectProperty LinkOf = dataFactory.getOWLObjectProperty(IRI.create(ontNameSpace+"LinkOf"));
		OWLObjectProperty ConnectTo = dataFactory.getOWLObjectProperty(IRI.create(ontNameSpace+"ConnectTo"));

		OWLClass prosumer_class = dataFactory.getOWLClass(IRI.create(ontNameSpace + "ESS"));

		Set<OWLNamedIndividual> prosumerIndividuals= GetDataFromOntology.getIndividualsOfClass(prosumer_class, reasoner);
		ArrayList<OWLNamedIndividual> proIndividuals= new ArrayList<OWLNamedIndividual> ();
		proIndividuals.addAll(prosumerIndividuals);
		
		OWLClass link_class = dataFactory.getOWLClass(IRI.create(ontNameSpace + "Link"));

		Set<OWLNamedIndividual> LinkIndividuals= GetDataFromOntology.getIndividualsOfClass(link_class, reasoner);
		ArrayList<OWLNamedIndividual> LIndividuals= new ArrayList<OWLNamedIndividual> ();
		LIndividuals.addAll(LinkIndividuals);
		
		for (int i=0; i<proIndividuals.size(); i++) {
			for (int j=0; j<proIndividuals.size(); j++) {
				
				if (proIndividuals.get(i).getIRI().getShortForm() .equals(prosumerName1) &  proIndividuals.get(j).getIRI().getShortForm() .equals(prosumerName2) 
						| proIndividuals.get(i).getIRI().getShortForm() .equals(prosumerName2) & proIndividuals.get(j).getIRI().getShortForm() .equals(prosumerName1)) {
					
					OWLObjectPropertyAssertionAxiom ConnectTo_property_Assertion = dataFactory.getOWLObjectPropertyAssertionAxiom(ConnectTo, proIndividuals.get(i), 
							proIndividuals.get(j));
					AddAxiom addaxiom= new AddAxiom(ontology, ConnectTo_property_Assertion);
					manager.applyChange(addaxiom);
					
					for (int k=0; k<LIndividuals.size(); k++) {
						if (LIndividuals.get(k).getIRI().getShortForm() .equals(nameOfLink)) {

							OWLObjectPropertyAssertionAxiom hasLink1_property_Assertion = dataFactory.getOWLObjectPropertyAssertionAxiom(hasLink, proIndividuals.get(i), 
									LIndividuals.get(k));
							AddAxiom addaxiom1= new AddAxiom(ontology, hasLink1_property_Assertion);
							manager.applyChange(addaxiom1);
							
							OWLObjectPropertyAssertionAxiom hasLink2_property_Assertion = dataFactory.getOWLObjectPropertyAssertionAxiom(hasLink, proIndividuals.get(j), 
									LIndividuals.get(k));
							AddAxiom addaxiom2= new AddAxiom(ontology, hasLink2_property_Assertion);
							manager.applyChange(addaxiom2);
							
							OWLObjectPropertyAssertionAxiom LinkOf1_property_Assertion = dataFactory.getOWLObjectPropertyAssertionAxiom(LinkOf, LIndividuals.get(k), 
									proIndividuals.get(i));
							AddAxiom addaxiom3= new AddAxiom(ontology, LinkOf1_property_Assertion);
							manager.applyChange(addaxiom3);
							
							OWLObjectPropertyAssertionAxiom LinkOf2_property_Assertion = dataFactory.getOWLObjectPropertyAssertionAxiom(LinkOf, LIndividuals.get(k),
									proIndividuals.get(j));
							AddAxiom addaxiom4= new AddAxiom(ontology, LinkOf2_property_Assertion);
							manager.applyChange(addaxiom4);
						}
					}
				}
			}
		}
	}
}
