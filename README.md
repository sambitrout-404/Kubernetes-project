# Kubernetes-project
Project related Kubernetes

A hands-on project demonstrating a full pipeline: GitHub → Docker → AWS ECR → Kubernetes.

###### DAY-1 ######
# Built Docker image, pushed to private AWS ECR.
# Created scoped IAM user (least privilege with policy- AmazonEC2ContainerRegistryPowerUser)
# Deployed image to local Kubernetes using imagePullSecrets
# Exposed via NodePort Service, verified with curl.

###### DAY-2 ######
- Created GitHub Actions workflow triggered on push to main.
- Configured AWS credentials via GitHub Secrets (no hardcoded keys).
- Workflow builds Docker image, pushes to ECR tagged with commit SHA + latest.
- Set ECR lifecycle policy: expire untagged images after 1 day, retain only last 5 "latest"-tagged images.

