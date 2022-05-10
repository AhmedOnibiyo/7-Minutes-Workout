package com.ahmedonibiyo.a7minutesworkout

object Constants {

    fun defaultExerciseList(): ArrayList<ExerciseModel> {
        val exerciseList = ArrayList<ExerciseModel>()
        val jumpingJacks = ExerciseModel(
            1,
            "Jumping Jacks",
            R.drawable.ic_jumping_jacks,
            isCompleted = false,
            false
        )
        exerciseList.add(jumpingJacks)

        val wallSit = ExerciseModel(
            2,
            "Wall Sit",
            R.drawable.ic_wall_sit,
            isCompleted = false,
            isSelected = false
        )
        exerciseList.add(wallSit)

        val pushUp = ExerciseModel(
            3,
            "Push Up",
            R.drawable.ic_push_up,
            isCompleted = false,
            isSelected = false
        )
        exerciseList.add(pushUp)

        val abdominalCrunch = ExerciseModel(
            4,
            "Abdominal Crunch",
            R.drawable.ic_abdominal_crunch,
            false,
            false
        )
        exerciseList.add(abdominalCrunch)

        val stepUpUntoChair = ExerciseModel(
            5,
            "Step-Up Onto Chair",
            R.drawable.ic_step_up_onto_chair,
            false,
            false
        )
        exerciseList.add(stepUpUntoChair)

        val squats = ExerciseModel(
            6,
            "Squats",
            R.drawable.ic_squat,
            false,
            false
        )
        exerciseList.add(squats)

        val tricepsDips = ExerciseModel(
            7,
            "Triceps Dips On Chair",
            R.drawable.ic_triceps_dip_on_chair,
            false,
            false
        )
        exerciseList.add(tricepsDips)

        val plank = ExerciseModel(
            8,
            "Plank",
            R.drawable.ic_plank,
            false,
            false
        )
        exerciseList.add(plank)

        val highKneesRunning = ExerciseModel(
            9,
            "High Knees Running In Place",
            R.drawable.ic_high_knees_running_in_place,
            false,
            false
        )
        exerciseList.add(highKneesRunning)

        val lunges = ExerciseModel(
            10,
            "Lunges",
            R.drawable.ic_lunge,
            false,
            false
        )
        exerciseList.add(lunges)

        val pushUpAndRotation = ExerciseModel(
            11, "Push-Up and Rotation", R.drawable.ic_push_up_and_rotation, false, false
        )
        exerciseList.add(pushUpAndRotation)

        val sidePlank = ExerciseModel(
            12, "Side Plank", R.drawable.ic_side_plank, false, false
        )
        exerciseList.add(sidePlank)

        return exerciseList
    }
}