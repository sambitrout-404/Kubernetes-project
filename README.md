# Kubernetes-project
Project related Kubernetes

A hands-on project demonstrating a full pipeline: GitHub → Docker → AWS ECR → Kubernetes.

###### DAY-1 ######
- Built Docker image, pushed to private AWS ECR.
- Created scoped IAM user (least privilege with policy- AmazonEC2ContainerRegistryPowerUser).
- Deployed image to local Kubernetes using imagePullSecrets.
- Exposed via NodePort Service, verified with curl.

###### DAY-2 ######
- Created GitHub Actions workflow triggered on push to main.
- Configured AWS credentials via GitHub Secrets (no hardcoded keys).
- Workflow builds Docker image, pushes to ECR tagged with commit SHA + latest.
- Set ECR lifecycle policy: expire untagged images after 1 day, retain only last 5 "latest"-tagged images.

###### DAY-3 ######
- Extended the workflow to update deployment.yaml with the new commit-SHA image tag using sed.
- Workflow commits the updated manifest back to the repo using github-actions[bot], with [skip ci] to avoid an infinite trigger loop.
- Set <imagePullPolicy: Always> for explicit, unambiguous pulls.

###### DAY-4 ######
- Applied the updated manifest manually and hit a real ImagePullBackOff — the ECR authentication token had expired (ECR tokens are valid for 12 hours).
- Diagnosed the root cause using kubectl describe pod → Events.
- Observed Kubernetes rolling update safety in action: the two old, healthy pods were not terminated while the new pod was failing — Kubernetes only replaces old pods once new ones pass health checks.
- Refreshed the imagePullSecret and completed the rollout successfully.


