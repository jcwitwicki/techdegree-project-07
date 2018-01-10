package com.teamtreehouse.instateam.validation;

        import com.teamtreehouse.instateam.service.CollaboratorService;
        import org.springframework.beans.factory.annotation.Autowired;
        import javax.validation.ConstraintValidator;
        import javax.validation.ConstraintValidatorContext;

public class UniqueCollaboratorValidator implements ConstraintValidator<UniqueCollaboratorConstraint, String> {

    @Autowired
    private CollaboratorService collaboratorService;

    @Autowired
    public UniqueCollaboratorValidator(CollaboratorService collaboratorService) {
        this.collaboratorService = collaboratorService;
    }

    @Override
    public void initialize(UniqueCollaboratorConstraint collaboratorAdded) {
    }


    @Override
    public boolean isValid(String name, ConstraintValidatorContext context) {
            return name != null && !collaboratorService.findByName(name).isPresent();
        }
    }
