# Kubernetes-project
Project related Kubernetes

A hands-on project demonstrating a full pipeline: GitHub → Docker → AWS ECR → Kubernetes.

###### DAY-1 ######
Built Docker image, pushed to private AWS ECR.
Created scoped IAM user (least privilege with policy- AmazonEC2ContainerRegistryPowerUser)
Deployed image to local Kubernetes using imagePullSecrets
Exposed via NodePort Service, verified with curl.
