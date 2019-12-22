package Ontologist;

import jade.core.Agent;
import jade.core.behaviours.Behaviour;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;

public class ReceivingRequestFromEss extends Behaviour {
	private Agent a;
	private OntPreps inputOnt=new OntPreps();

	public ReceivingRequestFromEss(Agent a) {
		super(a);
		this.a = a;
	}

	@Override
	public void onStart() {
		super.onStart();
		inputOnt.fileOpen("src/main/resources/ontxmas.owl");
		inputOnt.ontologyProcessing();
	}
	
	@Override
	public void action() {
		MessageTemplate mt = MessageTemplate.and(
				MessageTemplate.MatchPerformative(ACLMessage.REQUEST), 
				MessageTemplate.MatchProtocol("Ontolog"));
		ACLMessage request = a.receive(mt);

		if (request!=null) {

			System.out.println(a.getLocalName() + " received: " + request.getContent());

			String [] words = request.getContent().split(" ");
			OntPusher op = new OntPusher(inputOnt.returnOntObjects());
			op.writeProsumerIndividual(request.getSender().getLocalName());
			op.writeProsumerIndividual(words[2]);
//			System.out.println("Words length: " + words.length);
					String [] substrings1 = words[0].split("SS");
					String [] substrings2 = words[2].split("SS");

					int number1 = Integer.parseInt(substrings1[1]);
					int number2 = Integer.parseInt(substrings2[1]);

					String linkName= null;
					double weightOfLink = Double.parseDouble(words[5]);
					if (number1 < number2) {
						linkName="Link_"+number1+"_"+number2;
					}
					else {
						linkName="Link_"+number2+"_"+number1;
					}
					op.writeLinkIndividual_WithWeight(linkName, weightOfLink);
					op.writeObjectProperties(request.getSender().getLocalName(), words[2], linkName);
			inputOnt.outputtingOnt(true);
		}
		else {
			block();
		}
	}
	
	@Override
	public int onEnd() {
		System.out.println(":)");
		return super.onEnd();
	}

	@Override
	public boolean done() {
		// TODO Auto-generated method stub
		return false;
	}
}
