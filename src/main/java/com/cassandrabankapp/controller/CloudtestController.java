package com.cassandrabankapp.controller;

import java.util.HashMap;
import java.util.Map;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.amazonaws.regions.Regions;
import com.amazonaws.services.ec2.AmazonEC2;
import com.amazonaws.services.ec2.AmazonEC2ClientBuilder;
import com.amazonaws.services.ec2.model.DryRunResult;
import com.amazonaws.services.ec2.model.DryRunSupportedRequest;
import com.amazonaws.services.ec2.model.StartInstancesRequest;
import com.amazonaws.services.ec2.model.StopInstancesRequest;
import com.amazonaws.services.ec2.model.DescribeRegionsResult;
import com.amazonaws.services.ec2.model.Region;
import com.amazonaws.services.ec2.model.DescribeInstancesRequest;
import com.amazonaws.services.ec2.model.DescribeInstancesResult;
import com.amazonaws.services.ec2.model.Instance;
import com.amazonaws.services.ec2.model.Reservation;

import com.cassandrabankapp.dto.CloudtestForm;

@Controller
@RequestMapping("/cloudtest")
public class CloudtestController {

	private static Logger logger = LoggerFactory.getLogger(CloudtestController.class);
	
	@RequestMapping(method = RequestMethod.GET)
	public String showInsances(Model model) {
		CloudtestForm cloudtestForm = new CloudtestForm();
		HashMap <String, String> instances = showInstances();
		
		for (Map.Entry m:instances.entrySet())
	          System.out.println("Instance: " + m.getKey() + 
	                             " -> Status: " + m.getValue());
		
		//model.addAttribute("account", account);
		model.addAttribute("cloudtestForm",cloudtestForm);
		
		return "cloudtest";
	}
	
	@RequestMapping(value = "/start", method = RequestMethod.POST)
	public String postCloudtestForm(@ModelAttribute("cloudtestForm") @Valid CloudtestForm cloudtestForm, BindingResult result, RedirectAttributes redirectAttributes) {
		if(result.hasErrors()){
			logger.info("ERROR: "+ result.toString());
			return "cloudtest";
		}
		logger.info("Instance id : " + cloudtestForm.getInstanceID());
		
		startInstance(cloudtestForm.getInstanceID());
		redirectAttributes.addFlashAttribute("flashType", "success");
		redirectAttributes.addFlashAttribute("flashMessage","Instance: " + cloudtestForm.getInstanceID() + " Has been Started");
		return "redirect:/cloudtest";
	}
	
	@RequestMapping(value = "/stop", method = RequestMethod.POST)
	public String postCloudtestPage(@ModelAttribute("cloudtestForm") @Valid CloudtestForm cloudtestForm, BindingResult result, RedirectAttributes redirectAttributes) {
		if(result.hasErrors()){
			logger.info("ERROR: "+ result.toString());
			return "cloudtest";
		}
		logger.info("Instance id : " + cloudtestForm.getInstanceID());
		
		stopInstance(cloudtestForm.getInstanceID());
		redirectAttributes.addFlashAttribute("flashType", "failure");
		redirectAttributes.addFlashAttribute("flashMessage","Instance: " + cloudtestForm.getInstanceID() + " has been Stoped");
		return "redirect:/cloudtest";
	}
	
	public static void startInstance(String instance_id)
    {
        final AmazonEC2 ec2 = AmazonEC2ClientBuilder.standard()
                .withRegion(Regions.US_EAST_1)
                .build();

        DryRunSupportedRequest<StartInstancesRequest> dry_request =
            () -> {
            StartInstancesRequest request = new StartInstancesRequest()
                .withInstanceIds(instance_id);

            return request.getDryRunRequest();
        };

        DryRunResult dry_response = ec2.dryRun(dry_request);

        if(!dry_response.isSuccessful()) {
            System.out.printf(
                "Failed dry run to start instance %s", instance_id);

            throw dry_response.getDryRunResponse();
        }

        StartInstancesRequest request = new StartInstancesRequest()
            .withInstanceIds(instance_id);

        ec2.startInstances(request);

        System.out.printf("Successfully started instance %s", instance_id);
        System.out.println();
    }
	
	public static HashMap<String, String> showInstances() {
		HashMap <String, String> hmap = new HashMap<String, String>();  
		
		final AmazonEC2 ec2 = AmazonEC2ClientBuilder.standard()
                .withRegion(Regions.US_EAST_1)
                .build();
		boolean done = false;
		
		while(!done) {
		    DescribeInstancesRequest request = new DescribeInstancesRequest();
		    DescribeInstancesResult response = ec2.describeInstances(request);

		    for(Reservation reservation : response.getReservations()) {
		        for(Instance instance : reservation.getInstances()) {
		            System.out.printf(
		                "Found reservation with id %s, " +
		                "AMI %s, " +
		                "state %s ",
		                instance.getInstanceId(),
		                instance.getImageId(),
		                instance.getState().getName());
		            hmap.put(instance.getInstanceId(), instance.getState().getName());
		            System.out.println();
		        }
		    }

		    request.setNextToken(response.getNextToken());

		    if(response.getNextToken() == null) {
		        done = true;
		    }
		}
		return hmap;
	}

    public static void stopInstance(String instance_id)
    {
    	final AmazonEC2 ec2 = AmazonEC2ClientBuilder.standard()
                .withRegion(Regions.US_EAST_1)
                .build();
    	
        DryRunSupportedRequest<StopInstancesRequest> dry_request =
            () -> {
            StopInstancesRequest request = new StopInstancesRequest()
                .withInstanceIds(instance_id);

            return request.getDryRunRequest();
        };

        DryRunResult dry_response = ec2.dryRun(dry_request);

        if(!dry_response.isSuccessful()) {
            System.out.printf(
                "Failed dry run to stop instance %s", instance_id);
            throw dry_response.getDryRunResponse();
        }

        StopInstancesRequest request = new StopInstancesRequest()
            .withInstanceIds(instance_id);

        ec2.stopInstances(request);

        System.out.printf("Successfully stop instance %s", instance_id);
        System.out.println();
    }
}
