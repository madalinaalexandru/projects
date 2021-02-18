package tema2;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;
import java.util.Timer;

import javax.swing.*;
import javax.swing.border.*;

public class GUI{
	
	public double avgServiceTime(QueueArray store, int nbOfClients) {
		
		double avgServiceTime = 0;
		
		for(int i = 0; i < store.getStore().size(); i++) {
			for(int j = 0; j < store.getStore().get(i).getClients().size(); j++) {
				avgServiceTime += store.getStore().get(i).getClients().get(j).getServiceTime();
			}
		}
		
		avgServiceTime /= nbOfClients;
		
		return avgServiceTime;
		
	}
	
	public GUI() {
		
		JFrame frame = new JFrame("Queue");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(950, 450);
		
		JPanel panel = new JPanel();
		
		JPanel options = new JPanel();
		
		JPanel status = new JPanel();
		
		JPanel left = new JPanel();
		
		JPanel right = new JPanel();
		
		//simulation status panel
		
		JLabel stats = new JLabel("Status:");
		stats.setAlignmentX(stats.CENTER_ALIGNMENT);
		
		TextArea simStats = new TextArea();
		
		status.add(stats);
		status.add(simStats);
				
		status.setLayout(new BoxLayout(status, BoxLayout.Y_AXIS));
		
		//set options panel
		JLabel opt = new JLabel("Options:");
		opt.setAlignmentX(opt.CENTER_ALIGNMENT);
		
		JPanel buttons = new JPanel();
		
		JButton start = new JButton("Start");
		JButton ok = new JButton("OK");
		
		buttons.add(ok);
		buttons.add(start);
		
		//add queues settings
		JPanel setNbOfQueues = new JPanel();
				
		JLabel nbOfQueues = new JLabel("Number of queues = ");
		JTextField inNbOfQueues = new JTextField(5);
				
		setNbOfQueues.add(nbOfQueues);
		setNbOfQueues.add(inNbOfQueues);
		
		//set clients settings
		JPanel setNbOfClients = new JPanel();
		
		JLabel nbOfClients = new JLabel("Number of clients = ");
		JTextField inNbOfClients = new JTextField(5);
				
		setNbOfClients.add(nbOfClients);
		setNbOfClients.add(inNbOfClients);
		
		//set min service time panel
		JPanel minServiceTime = new JPanel();
		
		JLabel min = new JLabel("Minimum Service Time = ");
		JTextField inMin = new JTextField(5);
		
		minServiceTime.add(min);
		minServiceTime.add(inMin);
		
		//set max service time panel
		JPanel maxServiceTime = new JPanel();
		
		JLabel max = new JLabel("Maximum Service Time = ");
		JTextField inMax = new JTextField(5);
		
		maxServiceTime.add(max);
		maxServiceTime.add(inMax);
		
		//small option panel
		JPanel sOpt = new JPanel();
		
		sOpt.add(setNbOfQueues);
		sOpt.add(setNbOfClients);
		sOpt.add(minServiceTime);
		sOpt.add(maxServiceTime);
		sOpt.add(buttons);
		
		sOpt.setLayout(new BoxLayout(sOpt, BoxLayout.Y_AXIS));
		sOpt.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED));
		
		//set options panel
		options.add(opt);
		options.add(sOpt);
		
		options.setLayout(new BoxLayout(options, BoxLayout.Y_AXIS));
		
		//set right part of the big panel
		right.add(status);
		right.add(options);
		
		right.setAlignmentX(right.RIGHT_ALIGNMENT);
		right.setLayout(new BoxLayout(right, BoxLayout.Y_AXIS));
		
		//set left side panel
		
		ok.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				left.removeAll();
				
				JPanel queueNbPanel = new JPanel();
				JPanel queueStPanel = new JPanel();
				JPanel curClientsTimeLeftPanel = new JPanel();
				
				queueNbPanel.removeAll();
				queueStPanel.removeAll();
				curClientsTimeLeftPanel.removeAll();
				simStats.setText("");
				
				JLabel queueNb = new JLabel ("Queue Number");
				queueNb.setAlignmentX(queueNb.CENTER_ALIGNMENT);
				
				JLabel queueSt = new JLabel ("Queue Status");
				queueSt.setAlignmentX(queueSt.CENTER_ALIGNMENT);
				
				JLabel curClientTimeLeft = new JLabel ("Left Waiting time for current client");
				curClientTimeLeft.setAlignmentX(curClientTimeLeft.CENTER_ALIGNMENT);
				
				queueNb.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED), 
			            BorderFactory.createEmptyBorder(5, 5, 5, 5)));
				queueSt.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED), 
			            BorderFactory.createEmptyBorder(5, 5, 5, 5)));
				curClientTimeLeft.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED), 
			            BorderFactory.createEmptyBorder(5, 5, 5, 5)));
				
				queueNbPanel.add(queueNb);
				queueStPanel.add(queueSt);
				curClientsTimeLeftPanel.add(curClientTimeLeft);
				
				ArrayList<JTextField> qsNbList= new ArrayList<JTextField>();
				ArrayList<JTextField> qsList= new ArrayList<JTextField>();
				ArrayList<JTextField> timeList= new ArrayList<JTextField>();
				
				int nbOfQueues = Integer.parseInt(inNbOfQueues.getText());
				
				for(int i = 0; i < nbOfQueues; i++) {
					qsNbList.add(new JTextField(5));
					queueNbPanel.add(qsNbList.get(i));
					qsNbList.get(i).setEditable(false);
				}
				
				for(int i = 0; i < nbOfQueues; i++) {
					qsList.add(new JTextField(5));
					queueStPanel.add(qsList.get(i));
					qsList.get(i).setEditable(false);
					qsList.get(i).setHorizontalAlignment(SwingConstants.RIGHT);
				}
				
				for(int i = 0; i < nbOfQueues; i++) {
					timeList.add(new JTextField(5));
					curClientsTimeLeftPanel.add(timeList.get(i));
					timeList.get(i).setEditable(false);
				}
				
				queueNbPanel.setLayout(new BoxLayout(queueNbPanel, BoxLayout.Y_AXIS));
				queueStPanel.setLayout(new BoxLayout(queueStPanel, BoxLayout.Y_AXIS));
				curClientsTimeLeftPanel.setLayout(new BoxLayout(curClientsTimeLeftPanel, BoxLayout.Y_AXIS));
				
				left.add(queueNbPanel);
				left.add(queueStPanel);
				left.add(curClientsTimeLeftPanel);
				left.setLayout(new FlowLayout());
				
				int nbOfClients = Integer.parseInt(inNbOfClients.getText());
				int min = Integer.parseInt(inMin.getText());
				int max = Integer.parseInt(inMax.getText());
				
				QueueArray store = new QueueArray(nbOfQueues, nbOfClients, min, max);
				double avgST = avgServiceTime(store, nbOfClients);
				
				start.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						
						simStats.setText(simStats.getText() + "Simulation has started.\n");
						
						for(int i = 0; i < nbOfQueues; i++) {
							
							qsNbList.get(i).setText("" + (i+1));
							
						}
						
						
						Thread t = new Thread(new Runnable() {
							
							@Override
							public void run() {
								
								for(int i = 0; i < nbOfQueues; i++) {
									
									store.getStore().get(i).start();
								}
							}
						});
						
						int[] array = new int[nbOfQueues];
		            	
		            	for (int i = 0; i < nbOfQueues; i++) {
		            		array[i] = 0;
		            	}
						
						TimerTask timerTask = new TimerTask() {

				            @Override
				            public void run() {
				            	
				            	int i = 0;
				            	
				            	
				            	for(i = 0; i < nbOfQueues; i++) {
			
									qsList.get(i).setText("");
										
									for(int j = 0; j < store.getStore().get(i).getClients().size(); j++) {
										qsList.get(i).setText(qsList.get(i).getText() + "*");
											
									}
											
									timeList.get(i).setText("");
										
									if(!store.getStore().get(i).getClients().isEmpty()) {
										if(store.getStore().get(i).getClients().get(0).getServiceTime() != 0) {
											timeList.get(i).setText("" + (store.getStore().get(i).getClients().get(0).getServiceTime()));
											store.getStore().get(i).getClients().get(0).setServiceTime(store.getStore().get(i).getClients().get(0).getServiceTime() - 1);
										}
									}
									else {
										if(array[i] != 1) {
											simStats.setText(simStats.getText() + "Queue number " + store.getStore().get(i).getId() + " has ended.\n");
											array[i] = 1;
											}
												
										qsList.get(i).setText("");
										timeList.get(i).setText("0");
									}
								}
				            	
				            	int j = 0;
				            	for(j = 0; j < nbOfQueues; j++) {
				            		if(array[j] == 0)
				            			break;
				            	}
				            	
				            	if(j == nbOfQueues) {
				            		simStats.setText(simStats.getText() + "Simulation has ended.\nAverage waiting time was " + avgST + "\n\n");
				            		cancel();
				            	}
				            }
				        };
						
						Timer timer = new Timer();
						timer.scheduleAtFixedRate(timerTask, 0, 1000);
						
						t.start();
					}
				});
				panel.validate();
				panel.repaint();
			}
		});
		
		left.setLayout(new BoxLayout(left, BoxLayout.Y_AXIS));
		
		panel.add(left);
		panel.add(right);
		
		frame.setContentPane(panel);
		frame.setVisible(true);
	}
}