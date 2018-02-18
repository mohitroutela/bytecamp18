library('caret')
heart_df <- read.csv("tempfile8.csv", sep = ',', header = TRUE)

head(heart_df)
plot(heart_df)
str(heart_df)

plot(heart_df$Age, heart_df$heart.rate, col = heart_df$Disease)

set.seed(3033)
intrain <- createDataPartition(y = heart_df$Disease, p= 0.7, list = FALSE)
training <- heart_df[intrain,]
testing <- heart_df[-intrain,]
dim(training)
dim(testing)
anyNA(heart_df)
summary(heart_df)
training[["Disease"]] = factor(training[["Disease"]])
trctrl <- trainControl(method = "repeatedcv", number = 10,repeats = 3)
set.seed(3233)
#svmfit <- svm(Diseases ~., data = training, kernel = "linear", cost = .1, scale = FALSE)

svm_linear <- train(Disease ~.,data = training, method = "svmLinear",
                    trControl = trctrl,
                    preProcess = c("center","scale"),
                    cost = 0.1,
                    tuneLength = 10)
svm_linear


test_pred <- predict(svm_linear,newdata = testing)
test_pred
plot(test_pred)
confusionMatrix(test_pred, testing$Disease)
